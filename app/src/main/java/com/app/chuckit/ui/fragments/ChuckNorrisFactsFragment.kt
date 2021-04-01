package com.app.chuckit.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chuckit.R
import com.app.chuckit.adapters.ChuckNorrisFactsAdapter
import com.app.chuckit.databinding.FragmentChuckNorrisFactsBinding
import com.app.chuckit.viewModels.ChuckItViewModel

class ChuckNorrisFactsFragment : Fragment(R.layout.fragment_chuck_norris_facts) {

    private val chuckItViewModel by viewModels<ChuckItViewModel>()

    private val navigationController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentChuckNorrisFactsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChuckNorrisFactsBinding.bind(view)

        initiateLayoutManager()
        chuckItViewModel.getAllNorrisFacts()

        chuckItViewModel.isLoadingChuckNorrisFacts.observe(viewLifecycleOwner, { isLoading ->
            //TODO: Loading na tela de ChuckNorrisFacts
        })

        chuckItViewModel.chuckNorrisFacts.observe(viewLifecycleOwner, { chuckNorrisFacts ->
            val chuckNorrisFactsAdapter = ChuckNorrisFactsAdapter(chuckNorrisFacts)
            binding.recyclerViewChuckNorrisFacts.adapter = chuckNorrisFactsAdapter
        })
    }

    private fun initiateLayoutManager() {
        val linearLayoutManagerVertical =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

        binding.recyclerViewChuckNorrisFacts.layoutManager = linearLayoutManagerVertical
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_chuckit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search_chuck_norris_facts -> {
                val directions =
                    ChuckNorrisFactsFragmentDirections
                        .actionChuckNorrisFactsFragmentToSearchChuckNorrisFactsFragment()
                navigationController.navigate(directions)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}