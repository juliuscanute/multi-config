package com.juliuscanute.multiconfig.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.databinding.ListItemConfigurationBinding
import com.juliuscanute.multiconfig.ui.config.ConfigurationViewModel

class ConfigurationAdapter(
    private val viewModel: ConfigurationViewModel
) :
    ListAdapter<ConfigurationViewDataModel, ConfigurationAdapter.ConfigurationHolder>(
        ConfigurationDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigurationHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemConfigurationBinding>(
            layoutInflater,
            R.layout.list_item_configuration,
            parent,
            false
        )
        return ConfigurationHolder(binding)
    }

    override fun onBindViewHolder(holder: ConfigurationHolder, position: Int) {
        val config = getItem(position)
        holder.binding.configuration = config
        holder.binding.model = viewModel
    }

    inner class ConfigurationHolder(val binding: ListItemConfigurationBinding) :
        RecyclerView.ViewHolder(binding.root)

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
