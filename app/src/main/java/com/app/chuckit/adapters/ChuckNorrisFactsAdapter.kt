package com.app.chuckit.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chuckit.databinding.ItemChuckNorrisFactBinding
import com.app.chuckit.interfaces.ShareChuckNorrisFactClickListener
import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.utils.Constants.CHARACTER_LIMIT_TO_UPPERCASE
import com.app.chuckit.utils.Constants.TEXT_LARGE_SIZE


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

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chuckNorrisFact = chuckNorrisFacts[position]
        with(chuckNorrisFact) {
            if (chuckNorrisFact.value.length <= CHARACTER_LIMIT_TO_UPPERCASE)
                holder.itemBinding.textViewChuckNorrisFact.textSize = TEXT_LARGE_SIZE
            holder.itemBinding.textViewChuckNorrisFact.text = this.value

            holder.itemBinding.textViewChuckNorrisFactCategory.text =
                formatCategories(chuckNorrisFact.categories)

            holder.itemBinding.imageViewShareChuckNorrisFact.setOnClickListener {
                shareChuckNorrisFactClickListener.onShareChuckNorrisFactClickListener(chuckNorrisFact.url)
            }
        }
    }

    private fun formatCategories(categories: List<String>): String {
        if (categories.isEmpty()) return "#undefined"

        val stringBuilder = StringBuilder()

        for (category in categories) {
            stringBuilder.append("#$category ")
        }
        return stringBuilder.toString()
    }

    override fun getItemCount(): Int = chuckNorrisFacts.count()

    ////////////////////////////////////////////////////////////////////////////////////////////////

    inner class ViewHolder(val itemBinding: ItemChuckNorrisFactBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    ////////////////////////////////////////////////////////////////////////////////////////////////

}