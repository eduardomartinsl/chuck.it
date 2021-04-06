package com.app.chuckit.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.chuckit.R
import com.app.chuckit.adapters.CategoriesAdapter
import com.app.chuckit.adapters.SearchSugestionsAdapter
import com.app.chuckit.databinding.FragmentSearchChuckNorrisFactsBinding
import com.app.chuckit.interfaces.SearchItemClickListener
import com.app.chuckit.viewModels.ChuckItViewModel
import kotlinx.coroutines.launch

class SearchChuckNorrisFactsFragment : Fragment(R.layout.fragment_search_chuck_norris_facts),
    SearchItemClickListener {

    private val chuckItViewModel by viewModels<ChuckItViewModel>()
    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentSearchChuckNorrisFactsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchChuckNorrisFactsBinding.bind(view)

        initiateLayoutManager()

        chuckItViewModel.loadSearchSugestionsAndCategories()

        binding.editTextSearch.setOnEditorActionListener { textView, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {

                    val searchStr = textView.text.toString()

                    with(chuckItViewModel) {
                        this.saveSearchSugestion(searchStr)
                        viewLifecycleOwner.lifecycleScope.launch {
                            searchChuckNorrisFactsWithQuery(searchStr)
                        }
                        navigateToChuckNorrisFacts()
                    }
                    true
                }
                else -> false
            }
        }
        initiateObservers()
    }

    private fun initiateObservers() {
        chuckItViewModel.searchSugestions.observe(viewLifecycleOwner,
            {
                val searchSugestionsAdapter = SearchSugestionsAdapter(it, this)
                binding.recyclerViewSearchSugestions.adapter = searchSugestionsAdapter
            }
        )

        chuckItViewModel.categories.observe(viewLifecycleOwner,
            {
                val categoriesAdapter = CategoriesAdapter(it, this)
                binding.recyclerViewCategories.adapter = categoriesAdapter

            }
        )
    }

    private fun navigateToChuckNorrisFacts() {
        navController.popBackStack()
    }

    override fun onSearchItemClickListener(SearchStr: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            chuckItViewModel.searchChuckNorrisFactsWithQuery(SearchStr)
            navigateToChuckNorrisFacts()
        }
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
        binding.recyclerViewSearchSugestions.layoutManager = linearLayoutManagerVertical
    }
}