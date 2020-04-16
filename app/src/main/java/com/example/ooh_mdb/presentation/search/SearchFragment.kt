package com.example.ooh_mdb.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.Constants
import com.example.domain.model.Movie
import com.example.ooh_mdb.R
import com.example.ooh_mdb.databinding.FragmentSearchBinding


/**
 * A search fragment.
 */
class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var moviesAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter =
        ListAdapter() { view, position -> //  Navigate to selected item details
            val movies: List<Movie> = viewModel.movies.value ?: emptyList()
            val bundle = bundleOf(Constants.BUNDLE_MOVIE_ID_KEY to movies[position].imdbID)
            view.findNavController().navigate(
                R.id.action_searchFragment_to_detailsFragment,
                bundle
            )
            Log.e(
                "MyActivity",
                "Clicked on item  ${view.id} at position $position"
            )
        }

        // Get the viewModel
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        // Specify the current activity as the lifecycle owner of the binding.
        binding.setLifecycleOwner(this)

        // Observing viewModel to update adapter
        viewModel.movies.observe(viewLifecycleOwner, Observer { moviesAdapter.update(it ?: emptyList()) })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            val stringId = resources.getIdentifier(it, "string", activity?.packageName)
            Toast.makeText(activity,  getString(stringId), Toast.LENGTH_SHORT).show()
        })

        // RecyclerView node initialized here
        binding.recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = moviesAdapter
        }

        binding.searchField.apply {
            this.isActivated = true
            this.queryHint = getString(R.string.search_hint)
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null && query.length > Constants.MIN_SEARCH_LENGTH) {
                        viewModel.loadMovies(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null && newText.length > Constants.MIN_SEARCH_LENGTH) {
                        viewModel.loadMovies(newText)
                    }
                    return false
                }
            })
        }

    }

}
