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
import com.app.chuckit.adapters.ChuckNorrisFactsAdapter
import com.app.chuckit.databinding.FragmentChuckNorrisFactsBinding
import com.app.chuckit.interfaces.ShareChuckNorrisFactClickListener
import com.app.chuckit.viewModels.ChuckItViewModel

class ChuckNorrisFactsFragment : Fragment(R.layout.fragment_chuck_norris_facts),
    ShareChuckNorrisFactClickListener {

    private val chuckItViewModel by activityViewModels<ChuckItViewModel>()

    private val navigationController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentChuckNorrisFactsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChuckNorrisFactsBinding.bind(view)

        initiateLayoutManager()

        chuckItViewModel.loadSearchSugestionsAndCategories()

        chuckItViewModel.isLoadingFacts.observe(viewLifecycleOwner, { isLoadingFacts ->
            if (isLoadingFacts) {
                binding.loadingDots.visibility = VISIBLE
                binding.recyclerViewChuckNorrisFacts.visibility = GONE
            } else {
                binding.loadingDots.visibility = GONE
                binding.recyclerViewChuckNorrisFacts.visibility = VISIBLE
            }
        })

        chuckItViewModel.chuckNorrisFacts.observe(viewLifecycleOwner, { chuckNorrisFacts ->
            val chuckNorrisFactsAdapter = ChuckNorrisFactsAdapter(chuckNorrisFacts, this)
            binding.recyclerViewChuckNorrisFacts.adapter = chuckNorrisFactsAdapter
        })

        chuckItViewModel.loadingFactsError.observe(viewLifecycleOwner, { loadingFactsError ->
            if (loadingFactsError != null)
                Toast.makeText(requireContext(), loadingFactsError, Toast.LENGTH_LONG).show()
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

    override fun onShareChuckNorrisFactClickListener(factURL: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check this Chuck Norris Fact! $factURL")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}