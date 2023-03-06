package de.alekseipopov.vivirahomeassignment.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.alekseipopov.vivirahomeassignment.databinding.ItemSearchResponseBinding
import de.alekseipopov.vivirahomeassignment.ui.model.SearchResponseItem

class SearchResultViewHolder(private val binding: ItemSearchResponseBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: SearchResponseItem, onClickListener: SearchResultOnClickListener) {
        binding.apply {
            root.setOnClickListener {
                onClickListener.onClick(root, adapterPosition, item)
            }
            tvUsername.text = item.username
            tvRepoName.text = item.repoName
            tvRepoTitle.text = item.repoTitle
            tvDesc.text = item.repoDesc
            Glide.with(itemView)
                .load(item.userAvatar)
                .into(ivAvatar)
        }
    }

    companion object {
        fun create(parent: ViewGroup): SearchResultViewHolder {
            val binding = ItemSearchResponseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return SearchResultViewHolder(binding)
        }
    }
}