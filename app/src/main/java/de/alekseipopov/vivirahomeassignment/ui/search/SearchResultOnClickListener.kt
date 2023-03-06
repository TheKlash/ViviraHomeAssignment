package de.alekseipopov.vivirahomeassignment.ui.search

import android.view.View
import de.alekseipopov.vivirahomeassignment.ui.model.SearchResponseItem

interface SearchResultOnClickListener {
    fun onClick(view: View, position: Int, item: SearchResponseItem)
}