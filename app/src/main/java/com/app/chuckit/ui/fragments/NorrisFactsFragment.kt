package com.app.chuckit.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chuckit.R
import com.app.chuckit.adapters.NorrisFactsAdapter
import com.app.chuckit.databinding.FragmentNorrisFactsBinding
import com.app.chuckit.interfaces.ShareNorrisFactClickListener
import com.app.chuckit.viewModels.ChuckItViewModel

class NorrisFactsFragment : Fragment(R.layout.fragment_norris_facts),
    ShareNorrisFactClickListener {

    private val chuckItViewModel by activityViewModels<ChuckItViewModel>()
    private val navigationController by lazy { findNavController() }
    private lateinit var binding: FragmentNorrisFactsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNorrisFactsBinding.bind(view)

        initiateLayoutManager()

        chuckItViewModel.loadSearchHistoryAndCategories()

        chuckItViewModel.isLoadingFacts.observe(viewLifecycleOwner, { isLoadingFacts ->
            if (isLoadingFacts) {
                binding.loadingDots.visibility = VISIBLE
                binding.recyclerViewNorrisFacts.visibility = GONE
            } else {
                binding.loadingDots.visibility = GONE
                binding.recyclerViewNorrisFacts.visibility = VISIBLE
            }
        })

        chuckItViewModel.norrisFacts.observe(viewLifecycleOwner, { NorrisFacts ->
            val norrisFactsAdapter = NorrisFactsAdapter(NorrisFacts, this)
            binding.recyclerViewNorrisFacts.adapter = norrisFactsAdapter
        })

        chuckItViewModel.loadingNorrisFactsError.observe(
            viewLifecycleOwner,
            { loadingNorrisFactsError ->
                if (loadingNorrisFactsError != null)
                    Toast.makeText(requireContext(), loadingNorrisFactsError, Toast.LENGTH_LONG)
                        .show()
            })
    }

    override fun onResume() {
        super.onResume()

        chuckItViewModel.getAllNorrisFacts()
    }

    private fun initiateLayoutManager() {
        val linearLayoutManagerVertical =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

        binding.recyclerViewNorrisFacts.layoutManager = linearLayoutManagerVertical
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
            R.id.action_search_norris_facts -> {
                val directions =
                    NorrisFactsFragmentDirections
                        .actionNorrisFactsFragmentToSearchNorrisFactsFragment()

                navigationController.navigate(directions)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onShareNorrisFactClickListener(factURL: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check this Chuck Norris Fact! $factURL")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}