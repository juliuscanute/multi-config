package configuration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import builder.Configuration
import configuration.databinding.ListItemConfigurationBinding

class ConfigurationAdapter(
    private val viewModel: MainActivityViewModel
) :
    ListAdapter<Configuration, ConfigurationAdapter.ConfigurationHolder>(ConfigurationDiffUtil()) {

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
        holder.binding.configuration = getItem(position)
        holder.binding.model = viewModel
    }

    inner class ConfigurationHolder(val binding: ListItemConfigurationBinding) :
        RecyclerView.ViewHolder(binding.root)

}

class ConfigurationDiffUtil : DiffUtil.ItemCallback<Configuration>() {
    override fun areItemsTheSame(oldItem: Configuration, newItem: Configuration): Boolean {
        return oldItem.environment == newItem.environment

    }

    override fun areContentsTheSame(oldItem: Configuration, newItem: Configuration): Boolean {
        return oldItem == newItem
    }

}