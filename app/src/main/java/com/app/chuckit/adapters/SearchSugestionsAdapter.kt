package com.app.chuckit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chuckit.databinding.ItemSearchSugestionBinding
import com.app.chuckit.interfaces.SearchItemClickListener

class SearchSugestionsAdapter(
    private val searchSugestions: List<String>,
    private val searchItemClickListener: SearchItemClickListener
) :
    RecyclerView.Adapter<SearchSugestionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemSearchSugestionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchSugestion = searchSugestions[position]
        with(searchSugestion) {
            holder.itemBinding.textViewSearchSugestion.text = this

            holder.itemView.setOnClickListener {
                searchItemClickListener.onSearchItemClickListener(this)
            }
        }
    }

    override fun getItemCount(): Int = searchSugestions.count()

    ////////////////////////////////////////////////////////////////////////////////////////////////

    inner class ViewHolder(val itemBinding: ItemSearchSugestionBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    ////////////////////////////////////////////////////////////////////////////////////////////////

}