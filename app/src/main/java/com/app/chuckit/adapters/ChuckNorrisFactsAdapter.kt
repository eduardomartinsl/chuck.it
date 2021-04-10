package com.app.chuckit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chuckit.databinding.ItemChuckNorrisFactBinding
import com.app.chuckit.interfaces.ShareChuckNorrisFactClickListener
import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.utils.ChuckNorrisFactHelper


class ChuckNorrisFactsAdapter(
    private val chuckNorrisFacts: List<ChuckNorrisFact>,
    private val shareChuckNorrisFactClickListener: ShareChuckNorrisFactClickListener
) :
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

            holder.itemBinding.textViewChuckNorrisFact.textSize =
                ChuckNorrisFactHelper.getTextSize(chuckNorrisFact.value.length)

            holder.itemBinding.textViewChuckNorrisFact.text = this.value

            holder.itemBinding.textViewChuckNorrisFactCategory.text =
                ChuckNorrisFactHelper.formatCategories(chuckNorrisFact.categories)

            holder.itemBinding.imageViewShareChuckNorrisFact.setOnClickListener {
                shareChuckNorrisFactClickListener.onShareChuckNorrisFactClickListener(
                    chuckNorrisFact.url
                )
            }
        }
    }

    override fun getItemCount(): Int = chuckNorrisFacts.count()

    ////////////////////////////////////////////////////////////////////////////////////////////////

    inner class ViewHolder(val itemBinding: ItemChuckNorrisFactBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    ////////////////////////////////////////////////////////////////////////////////////////////////

}