package com.example.fufu.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fufu.asset.Helper
import com.example.fufu.data.model.Resource
import com.example.fufu.databinding.RestaurantResourceItemLayoutBinding

class RestaurantResourceItemAdapter(private var resources: List<Resource>): RecyclerView.Adapter<RestaurantResourceItemAdapter.ViewHolder>() {
    fun setResources(_resources: List<Resource>) {
        resources = _resources
        resources.map { it.createdAt = Helper().getDate(it.createdAt) }
    }

    class ViewHolder(binding: RestaurantResourceItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        private val bindingItem = binding
        fun bind(resource: Resource) {
            bindingItem.resource = resource
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RestaurantResourceItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resources[position])
    }

    override fun getItemCount(): Int {
        return resources.size
    }

}