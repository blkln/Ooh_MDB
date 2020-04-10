package com.example.ooh_mdb.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.MoviesRepository
import com.example.domain.Movie
import com.example.interactors.GetMovies
import com.example.ooh_mdb.R
import com.example.ooh_mdb.databinding.FragmentSearchBinding
import com.example.ooh_mdb.framework.MovieFetcher
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * A search fragment.
 */
class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private val moviesAdapter: ListAdapter = ListAdapter() { view, position -> //  Navigate to selected item details
        val movies : List<Movie> = viewModel.movies.value ?: emptyList()
        val bundle = bundleOf("imdbID" to movies[position].imdbID)
        view.findNavController().navigate(
            R.id.action_searchFragment_to_detailsFragment,
            bundle
        )
        Log.e(
            "MyActivity",
            "Clicked on item  ${view.id} at position $position"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search, container, false)

        // Get the viewModel
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        // Specify the current activity as the lifecycle owner of the binding.
        binding.setLifecycleOwner(this)

        // Observing viewModel to update adapter
        viewModel.movies.observe(viewLifecycleOwner, Observer { moviesAdapter.update(it ?: emptyList()) })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            this.queryHint = "Enter movie name"
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null && query.length > 3) {
                        viewModel.loadMovies(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null && newText.length > 3) {
                        viewModel.loadMovies(newText)
                    }
                    return false
                }
            })
        }

    }

}
