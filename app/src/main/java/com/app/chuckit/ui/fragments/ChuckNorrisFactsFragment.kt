package com.app.chuckit.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.chuckit.R
import com.app.chuckit.viewModels.ChuckItViewModel

class ChuckNorrisFactsFragment : Fragment(R.layout.fragment_chuck_norris_facts) {

    private val chuckItViewModel by viewModels<ChuckItViewModel>()

    private val navigationController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chuckItViewModel.chuckNorrisFacts.observe(this, Observer {
            // TODO: Prencher recyclerView com ChuckNorrisFacts
        })

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
                    ChuckNorrisFactsFragmentDirections.actionChuckNorrisFactsFragmentToSearchChuckNorrisFactsFragment()
                navigationController.navigate(directions)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}