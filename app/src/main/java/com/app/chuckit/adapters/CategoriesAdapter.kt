package com.app.chuckit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chuckit.databinding.ItemCategoryBinding

class CategoriesAdapter(private val categories: List<String>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = categories.count()

    ////////////////////////////////////////////////////////////////////////////////////////////////

    inner class ViewHolder(private val itemBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(category: String) {
            itemBinding.textViewCategory.text = category
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}