package com.app.chuckit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chuckit.databinding.ItemCategoryBinding
import com.app.chuckit.interfaces.SearchItemClickListener

class CategoriesAdapter(
    private val categories: List<String>,
    private val searchItemClickListener: SearchItemClickListener
) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        with(category) {
            holder.itemBinding.textViewCategory.text = this

            holder.itemView.setOnClickListener {
                searchItemClickListener.onSearchItemClickListener(this)
            }
        }
    }

    override fun getItemCount(): Int = categories.count()

    ////////////////////////////////////////////////////////////////////////////////////////////////

    inner class ViewHolder(val itemBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    ////////////////////////////////////////////////////////////////////////////////////////////////

}