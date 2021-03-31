package com.app.chuckit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chuckit.databinding.ItemChuckNorrisFactBinding
import com.app.chuckit.models.ChuckNorrisFact

class ChuckNorrisFactsAdapter(private val chuckNorrisFacts: List<ChuckNorrisFact>) :
    RecyclerView.Adapter<ChuckNorrisFactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemChuckNorrisFactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chuckNorrisFact = chuckNorrisFacts[position]
        holder.bind(chuckNorrisFact)
    }

    override fun getItemCount(): Int = chuckNorrisFacts.count()

    ////////////////////////////////////////////////////////////////////////////////////////////////

    inner class ViewHolder(private val itemBinding: ItemChuckNorrisFactBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(chuckNorrisFact: ChuckNorrisFact) {
            itemBinding.textViewChuckNorrisFact.text = chuckNorrisFact.value
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}