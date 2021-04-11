package com.app.chuckit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chuckit.databinding.ItemNorrisFactBinding
import com.app.chuckit.interfaces.ShareNorrisFactClickListener
import com.app.chuckit.models.NorrisFact
import com.app.chuckit.utils.NorrisFactHelper


class NorrisFactsAdapter(
    private val norrisFacts: List<NorrisFact>,
    private val shareNorrisFactClickListener: ShareNorrisFactClickListener
) :
    RecyclerView.Adapter<NorrisFactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNorrisFactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val norrisFact = norrisFacts[position]

        with(norrisFact) {
            holder.itemBinding.textViewChuckNorrisFact.text = this.value

            holder.itemBinding.textViewChuckNorrisFact.textSize =
                NorrisFactHelper.getTextSize(norrisFact.value.length)

            holder.itemBinding.textViewChuckNorrisFactCategory.text =
                NorrisFactHelper.formatCategories(norrisFact.categories)

            holder.itemBinding.imageViewShareChuckNorrisFact.setOnClickListener {
                shareNorrisFactClickListener.onShareNorrisFactClickListener(
                    norrisFact.url
                )
            }
        }
    }

    override fun getItemCount(): Int = norrisFacts.count()

    ////////////////////////////////////////////////////////////////////////////////////////////////

    inner class ViewHolder(val itemBinding: ItemNorrisFactBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    ////////////////////////////////////////////////////////////////////////////////////////////////

}