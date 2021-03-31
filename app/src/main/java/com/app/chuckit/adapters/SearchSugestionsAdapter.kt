package com.app.chuckit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chuckit.databinding.ItemSearchSugestionBinding
import com.app.chuckit.interfaces.ItemClickListener

class SearchSugestionsAdapter(
    private val searchSugestions: List<String>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<SearchSugestionsAdapter.ViewHolder>() {

    lateinit var itemBinding: ItemSearchSugestionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemBinding = ItemSearchSugestionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(searchSugestions[position]) {

            itemBinding.textViewSearchSugestion.text = this

            holder.itemView.setOnClickListener {
                itemClickListener.onItemClickListener(this)
            }
        }
    }

    override fun getItemCount(): Int = searchSugestions.count()

    ////////////////////////////////////////////////////////////////////////////////////////////////

    inner class ViewHolder(itemBinding: ItemSearchSugestionBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    ////////////////////////////////////////////////////////////////////////////////////////////////

}