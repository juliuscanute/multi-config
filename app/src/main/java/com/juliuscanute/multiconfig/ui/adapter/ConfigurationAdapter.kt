package com.juliuscanute.multiconfig.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.ui.config.ConfigurationViewModel
import com.juliuscanute.multiconfig.utils.getThemeColorId

class ConfigurationAdapter(
    private val viewModel: ConfigurationViewModel
) :
    ListAdapter<ConfigurationViewDataModel, ConfigurationAdapter.ConfigurationHolder>(
        ConfigurationDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigurationHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.com_juliuscanute_multiconfig_list_item_configuration, parent, false)
        return ConfigurationHolder(view)
    }

    override fun onBindViewHolder(holder: ConfigurationHolder, position: Int) {
        val config = getItem(position)
        val viewContext = holder.container.context
        if (config.selected) {
            holder.container.setBackgroundColor(
                ContextCompat.getColor(
                    viewContext,
                    viewContext.getThemeColorId(R.attr.colorAccent)
                )
            )
        } else {
            holder.container.setBackgroundColor(
                ContextCompat.getColor(
                    viewContext,
                    viewContext.getThemeColorId(android.R.attr.selectableItemBackground)
                )
            )
        }
        holder.container.setOnClickListener {
            viewModel.moveToConfigurationDetail(config.environment)
        }
        holder.container.setOnLongClickListener {
            viewModel.selectNewConfiguration(config.index)
        }
        holder.environment.text = config.environment
    }

    inner class ConfigurationHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(R.id.config_item_layout)
        val environment: TextView = view.findViewById(R.id.environment)
    }

}

class ConfigurationDiffUtil : DiffUtil.ItemCallback<ConfigurationViewDataModel>() {
    override fun areItemsTheSame(oldItem: ConfigurationViewDataModel, newItem: ConfigurationViewDataModel): Boolean {
        return oldItem.environment == newItem.environment

    }

    override fun areContentsTheSame(oldItem: ConfigurationViewDataModel, newItem: ConfigurationViewDataModel): Boolean {
        return oldItem == newItem
    }

}

data class ConfigurationViewDataModel(val index: Int, val environment: String, var selected: Boolean = false)
