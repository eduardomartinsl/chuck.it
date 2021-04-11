package com.app.chuckit.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.chuckit.R
import com.app.chuckit.adapters.CategoriesAdapter
import com.app.chuckit.adapters.SearchHistoryAdapter
import com.app.chuckit.databinding.FragmentSearchNorrisFactsBinding
import com.app.chuckit.interfaces.SearchItemClickListener
import com.app.chuckit.viewModels.ChuckItViewModel

class SearchNorrisFactsFragment : Fragment(R.layout.fragment_search_norris_facts),
    SearchItemClickListener {

    private val chuckItViewModel by activityViewModels<ChuckItViewModel>()
    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentSearchNorrisFactsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchNorrisFactsBinding.bind(view)

        initiateLayoutManager()

        chuckItViewModel.loadSearchHistoryAndCategories()

        binding.editTextSearch.setOnEditorActionListener { textView, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {

                    val searchStr = textView.text.toString()

                    with(chuckItViewModel) {
                        this.saveSearchHistory(searchStr)
                        this.searchNorrisFactsWithQuery(searchStr)
                        navigateToNorrisFacts()
                    }
                    true
                }
                else -> false
            }
        }

        binding.buttonReloadCategories.setOnClickListener {
            chuckItViewModel.getAllCategories()
        }

        chuckItViewModel.searchHistory.observe(viewLifecycleOwner,
            {
                val searchHistoryAdapter = SearchHistoryAdapter(it, this)
                binding.recyclerViewSearchHistory.adapter = searchHistoryAdapter
            }
        )

        chuckItViewModel.categories.observe(viewLifecycleOwner,
            {
                val categoriesAdapter = CategoriesAdapter(it, this)
                binding.recyclerViewCategories.adapter = categoriesAdapter

            }
        )

        chuckItViewModel.areCategoriesAvaliable.observe(
            viewLifecycleOwner,
            { areCategoriesAvaliable ->
                if (areCategoriesAvaliable != null) {
                    if (!areCategoriesAvaliable) {
                        binding.buttonReloadCategories.visibility = VISIBLE
                    } else {
                        binding.buttonReloadCategories.visibility = GONE
                    }
                }
            })

        chuckItViewModel.isLoadingCategories.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                binding.loadingDotsCategories.visibility = VISIBLE
                binding.buttonReloadCategories.visibility = GONE
                binding.recyclerViewCategories.visibility = GONE
            } else {
                binding.loadingDotsCategories.visibility = GONE
                binding.recyclerViewCategories.visibility = VISIBLE
            }
        })
    }

    private fun navigateToNorrisFacts() {
        navController.popBackStack()
    }

    override fun onSearchItemClickListener(SearchStr: String) {
        chuckItViewModel.searchNorrisFactsWithQuery(SearchStr)
        navigateToNorrisFacts()
    }

    private fun initiateLayoutManager() {

        val gridLayoutRowSpanCount = 2

        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(
                gridLayoutRowSpanCount,
                StaggeredGridLayoutManager.HORIZONTAL,
            )
        binding.recyclerViewCategories.layoutManager = staggeredGridLayoutManager

        val linearLayoutManagerVertical =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        binding.recyclerViewSearchHistory.layoutManager = linearLayoutManagerVertical
    }
}