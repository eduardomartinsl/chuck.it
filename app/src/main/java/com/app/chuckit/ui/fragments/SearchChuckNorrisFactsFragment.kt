package com.app.chuckit.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.chuckit.R
import com.app.chuckit.interfaces.ItemClickListener
import com.app.chuckit.viewModels.ChuckItViewModel

class SearchChuckNorrisFactsFragment : Fragment(R.layout.fragment_search_chuck_norris_facts),
    ItemClickListener {

    private val chuckItViewModel by viewModels<ChuckItViewModel>()

    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chuckItViewModel.loadSearchSugestionsAndCategories()

        chuckItViewModel.searchSugestions.observe(this, Observer {
            // TODO: Atribuir lista de sugestões na view (Criar recyclerView)
        })

        chuckItViewModel.categories.observe(this, Observer {
            // TODO: Atribuir lista de categorias (Criar recyclerView)
        })
    }

    private fun navigateToChuckNorrisFactsWithSearchQuery() {
        val direction =
            SearchChuckNorrisFactsFragmentDirections.actionSearchChuckNorrisFactsFragmentToChuckNorrisFactsFragment()
        navController.navigate(direction)
    }

    override fun onSugestionClickListener(sugestion: String) {
        chuckItViewModel.searchChuckNorrisFactsWithQuery(sugestion)
        navigateToChuckNorrisFactsWithSearchQuery()
    }

    override fun onCategoryClickListener(category: String) {
        chuckItViewModel.searchChuckNorrisFactsWithQuery(category)
        navigateToChuckNorrisFactsWithSearchQuery()
    }
}