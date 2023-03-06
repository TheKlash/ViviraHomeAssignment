package de.alekseipopov.vivirahomeassignment.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import de.alekseipopov.vivirahomeassignment.ui.model.SearchResponseItem

class SearchResultRecyclerAdapter(private val onClickListener: SearchResultOnClickListener) :
    ListAdapter<SearchResponseItem, SearchResultViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<SearchResponseItem>() {
        override fun areItemsTheSame(oldItem: SearchResponseItem, newItem: SearchResponseItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SearchResponseItem, newItem: SearchResponseItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder =
        SearchResultViewHolder.create(parent)

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = getItem(position)
        if (item is SearchResponseItem) {
            holder.bind(item, onClickListener)
        }
    }
}