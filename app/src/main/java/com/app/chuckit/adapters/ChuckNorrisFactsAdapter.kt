package com.app.chuckit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chuckit.databinding.ItemChuckNorrisFactBinding
import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.utils.Constants.CHARACTER_LIMIT_TO_UPPERCASE
import com.app.chuckit.utils.Constants.TEXT_LARGE_SIZE

class ChuckNorrisFactsAdapter(private val chuckNorrisFacts: List<ChuckNorrisFact>) :
    RecyclerView.Adapter<ChuckNorrisFactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemChuckNorrisFactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chuckNorrisFact = chuckNorrisFacts[position]
        with(chuckNorrisFact) {
            if (chuckNorrisFact.value.length <= CHARACTER_LIMIT_TO_UPPERCASE)
                holder.itemBinding.textViewChuckNorrisFact.textSize = TEXT_LARGE_SIZE
            holder.itemBinding.textViewChuckNorrisFact.text = this.value
        }
    }

    override fun getItemCount(): Int = chuckNorrisFacts.count()

    ////////////////////////////////////////////////////////////////////////////////////////////////

    inner class ViewHolder(val itemBinding: ItemChuckNorrisFactBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    ////////////////////////////////////////////////////////////////////////////////////////////////

}