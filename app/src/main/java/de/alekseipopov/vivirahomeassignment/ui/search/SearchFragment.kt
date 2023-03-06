package de.alekseipopov.vivirahomeassignment.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.alekseipopov.vivirahomeassignment.databinding.FragmentSearchBinding
import de.alekseipopov.vivirahomeassignment.ui.model.SearchResponseItem

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var binding: FragmentSearchBinding? = null

    private var adapter: SearchResultRecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            btnSearch.setOnClickListener {
                viewModel.resetCounter()
                viewModel.search(etSearch.text.toString())
            }
            btnPrev.setOnClickListener { viewModel.searchPreviousPage(etSearch.text.toString()) }
            btnNext.setOnClickListener { viewModel.searchNextPage(etSearch.text.toString()) }

            val onClickListener = object : SearchResultOnClickListener {
                override fun onClick(view: View, position: Int, item: SearchResponseItem) {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW, Uri.parse(item.repoUrl)
                    )
                    startActivity(urlIntent)
                }
            }
            adapter = SearchResultRecyclerAdapter(onClickListener)
            rvSearchResult.adapter = adapter

            viewModel.apply {
                searchResults.observe(viewLifecycleOwner) {
                    adapter?.submitList(it)
                }

                showLoading.observe(viewLifecycleOwner) {
                    binding?.progressBar?.isVisible = it
                }

                pageCounter.observe(viewLifecycleOwner) {
                    tvPageCounter.isVisible = it != null
                    tvPageCounter.text = it
                }

                showPrevButton.observe(viewLifecycleOwner) {
                    btnPrev.visibility = if (!it) View.INVISIBLE else View.VISIBLE
                }

                showNextButton.observe(viewLifecycleOwner) {
                    btnNext.visibility = if (!it) View.INVISIBLE else View.VISIBLE
                }

                errorMessage.observe(viewLifecycleOwner) {
                    if (it != null) {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}