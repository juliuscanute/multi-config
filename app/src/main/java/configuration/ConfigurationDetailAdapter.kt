package configuration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import configuration.databinding.ListItemChoiceBinding
import configuration.databinding.ListItemEditBinding
import configuration.databinding.ListItemRangeBinding
import configuration.databinding.ListItemSwitchBinding
import model.Item

class ConfigurationDetailAdapter(private val viewModel: MainActivityViewModel) :
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
                val binding = DataBindingUtil.inflate<ListItemSwitchBinding>(
                    layoutInflater,
                    R.layout.list_item_switch,
                    parent,
                    false
                )
                ListItemSwitchHolder(binding)
            }
            UiType.RANGE.type -> {
                val binding = DataBindingUtil.inflate<ListItemRangeBinding>(
                    layoutInflater,
                    R.layout.list_item_range,
                    parent,
                    false
                )
                ListItemRangeHolder(binding)
            }
            UiType.EDITABLE.type -> {
                val binding = DataBindingUtil.inflate<ListItemEditBinding>(
                    layoutInflater,
                    R.layout.list_item_edit,
                    parent,
                    false
                )
                ListItemEditableHolder(binding)
            }
            UiType.CHOICE.type -> {
                val binding = DataBindingUtil.inflate<ListItemChoiceBinding>(
                    layoutInflater,
                    R.layout.list_item_choice,
                    parent,
                    false
                )
                ListItemChoiceHolder(binding)
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
                holder.binding.model = item
                holder.binding.viewModel = viewModel
            }
            (item is ItemState.RangeState) && (holder is ListItemRangeHolder) -> {
                holder.binding.model = item
                holder.binding.viewModel = viewModel
            }
            (item is ItemState.EditableState) && (holder is ListItemEditableHolder) -> {
                holder.binding.model = item
                holder.binding.viewModel = viewModel
            }
            (item is ItemState.ChoiceState) && (holder is ListItemChoiceHolder) -> {
                holder.binding.model = item
                holder.binding.viewModel = viewModel
            }
        }
    }

    inner class ListItemSwitchHolder(val binding: ListItemSwitchBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ListItemRangeHolder(val binding: ListItemRangeBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ListItemEditableHolder(val binding: ListItemEditBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ListItemChoiceHolder(val binding: ListItemChoiceBinding) :
        RecyclerView.ViewHolder(binding.root)

}

class ConfigurationDetailDiffUtil : DiffUtil.ItemCallback<ItemState>() {

    override fun areItemsTheSame(oldItem: ItemState, newItem: ItemState) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: ItemState, newItem: ItemState): Boolean {
        return when {
            oldItem is ItemState.SwitchState && newItem is ItemState.SwitchState -> oldItem.switchValue == newItem.switchValue
            oldItem is ItemState.ChoiceState && newItem is ItemState.ChoiceState -> oldItem.currentChoiceIndex == newItem.currentChoiceIndex
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
        val step: Int,
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