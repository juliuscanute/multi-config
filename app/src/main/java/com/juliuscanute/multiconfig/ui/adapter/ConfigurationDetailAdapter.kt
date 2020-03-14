package com.juliuscanute.multiconfig.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.ui.configdetail.ConfigurationDetailViewModel
import model.Item
import model.Projection

class ConfigurationDetailAdapter(private val viewModel: ConfigurationDetailViewModel) :
    ListAdapter<ItemState, RecyclerView.ViewHolder>(ConfigurationDetailDiffUtil()) {

    enum class UiType(val type: Int) { SWITCH(0), RANGE(1), EDITABLE(2), CHOICE(3) }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ItemState.SwitchState -> UiType.SWITCH.type
            is ItemState.RangeState -> UiType.RANGE.type
            is ItemState.EditableState -> UiType.EDITABLE.type
            is ItemState.ChoiceState -> UiType.CHOICE.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            UiType.SWITCH.type -> {
                val view = layoutInflater.inflate(R.layout.com_juliuscanute_multiconfig_list_item_switch, parent, false)
                ListItemSwitchHolder(view)
            }
            UiType.RANGE.type -> {
                val view = layoutInflater.inflate(R.layout.com_juliuscanute_multiconfig_list_item_range, parent, false)
                ListItemRangeHolder(view)
            }
            UiType.EDITABLE.type -> {
                val view = layoutInflater.inflate(R.layout.com_juliuscanute_multiconfig_list_item_edit, parent, false)
                ListItemEditableHolder(view)
            }
            UiType.CHOICE.type -> {
                val view = layoutInflater.inflate(R.layout.com_juliuscanute_multiconfig_list_item_choice, parent, false)
                ListItemChoiceHolder(view)
            }
            else -> {
                throw NotImplementedError("Must not occur")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when {
            (item is ItemState.SwitchState) && (holder is ListItemSwitchHolder) -> {
                holder.container.setOnClickListener {
                    viewModel.saveBooleanConfiguration(item.key, item.switchValue)
                }
                holder.overline.text = item.description
                holder.content.text = item.getSwitchStatus()
                holder.switch.isChecked = item.switchValue
                holder.switch.setOnClickListener {
                    viewModel.saveBooleanConfiguration(item.key, item.switchValue)
                }
            }
            (item is ItemState.RangeState) && (holder is ListItemRangeHolder) -> {
                val projection =
                    Projection(userMax = item.max, userMin = item.min)
                projection.userValue = item.currentValue
                holder.rangeOverline.text = item.description
                holder.rangeCurrentValue.text = item.getValueString()
                holder.rangeMinValue.text = item.getMinString()
                holder.rangeMaxValue.text = item.getMaxString()
                holder.rangeSeekBar.progress = projection.progressValue
                holder.rangeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        if (fromUser) {
                            projection.progressValue = progress
                            holder.rangeCurrentValue.text = projection.userValue.toString()
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        viewModel.saveIntConfiguration(item.key, projection.userValue)
                    }
                })
            }
            (item is ItemState.EditableState) && (holder is ListItemEditableHolder) -> {
                holder.editContainer.setOnClickListener {
                    viewModel.showEditableDialog(item.description, item.currentValue, item.key)
                }
                holder.editOverline.text = item.description
                holder.editContent.text = item.currentValue
            }
            (item is ItemState.ChoiceState) && (holder is ListItemChoiceHolder) -> {
                holder.choiceContainer.setOnClickListener {
                    viewModel.showChoiceDialog(item.description, item.items, item.currentChoiceIndex, item.key)
                }
                holder.choiceOverline.text = item.description
                holder.choiceContent.text = item.getSelectedItem()
            }
        }
    }

    inner class ListItemSwitchHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(R.id.choice_container)
        val overline: TextView = view.findViewById(R.id.choice_overline)
        val content: TextView = view.findViewById(R.id.choice_content)
        val switch: Switch = view.findViewById(R.id.switch_flag)
    }

    inner class ListItemRangeHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
        val rangeOverline: TextView = view.findViewById(R.id.range_overline)
        val rangeCurrentValue: TextView = view.findViewById(R.id.range_current_value)
        val rangeSeekBar: AppCompatSeekBar = view.findViewById(R.id.range_seek_bar)
        val rangeMinValue: TextView = view.findViewById(R.id.range_min_value)
        val rangeMaxValue: TextView = view.findViewById(R.id.range_max_value)
    }

    inner class ListItemEditableHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
        val editContainer: View = view.findViewById(R.id.edit_container)
        val editOverline: TextView = view.findViewById(R.id.edit_overline)
        val editContent: TextView = view.findViewById(R.id.edit_content)
    }

    inner class ListItemChoiceHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
        val choiceContainer: View = view.findViewById(R.id.choice_container)
        val choiceOverline: TextView = view.findViewById(R.id.choice_overline)
        val choiceContent: TextView = view.findViewById(R.id.choice_content)
    }

}

class ConfigurationDetailDiffUtil : DiffUtil.ItemCallback<ItemState>() {

    override fun areItemsTheSame(oldItem: ItemState, newItem: ItemState) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: ItemState, newItem: ItemState): Boolean {
        return when {
            oldItem is ItemState.SwitchState && newItem is ItemState.SwitchState -> oldItem.switchValue == newItem.switchValue
            oldItem is ItemState.ChoiceState && newItem is ItemState.ChoiceState -> oldItem.currentChoiceIndex == newItem.currentChoiceIndex
                    && oldItem.description == newItem.description
            oldItem is ItemState.EditableState && newItem is ItemState.EditableState -> oldItem.currentValue == newItem.currentValue
            oldItem is ItemState.RangeState && newItem is ItemState.RangeState -> oldItem.currentValue == newItem.currentValue
            else -> false
        }
    }
}

sealed class ItemState(val id: String) {
    data class SwitchState(val key: String, val description: String, var switchValue: Boolean) :
        ItemState(id = key) {
        fun getSwitchStatus() = if (switchValue) "On" else "Off"
    }

    class RangeState(
        val key: String,
        val description: String,
        val min: Int,
        val max: Int,
        var currentValue: Int
    ) : ItemState(id = key) {

        fun getMinString() = min.toString()
        fun getMaxString() = max.toString()
        fun getValueString() = currentValue.toString()
    }

    data class EditableState(
        val key: String,
        val description: String,
        var currentValue: String
    ) : ItemState(id = key)


    data class ChoiceState(
        val key: String,
        val description: String,
        val items: ArrayList<Item>,
        var currentChoiceIndex: Int
    ) : ItemState(id = key) {
        fun getSelectedItem() = items[currentChoiceIndex].description
    }
}