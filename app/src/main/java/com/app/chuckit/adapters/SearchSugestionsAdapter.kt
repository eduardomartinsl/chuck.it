package com.app.chuckit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chuckit.databinding.ItemSearchSugestionBinding

class SearchSugestionsAdapter(private val searchSugestions: List<String>) :
    RecyclerView.Adapter<SearchSugestionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemSearchSugestionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchSugestion = searchSugestions[position]
        holder.bind(searchSugestion)
    }

    override fun getItemCount(): Int = searchSugestions.count()

    ////////////////////////////////////////////////////////////////////////////////////////////////

    class ViewHolder(private val itemBinding: ItemSearchSugestionBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(searchSugestions: String){
            itemBinding.textViewSearchSugestion.text = searchSugestions
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}