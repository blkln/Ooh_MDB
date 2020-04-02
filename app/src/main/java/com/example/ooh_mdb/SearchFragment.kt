package com.example.ooh_mdb

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.ooh_mdb.databinding.FragmentSearchBinding
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private val movieFetcher = MovieFetcher()

    private val callback = object : Callback<MovieResult> {
        override fun onFailure(call: Call<MovieResult>?, t: Throwable?) {
            Log.e("SearchFragment", "Problem calling OMDb API", t)
        }

        override fun onResponse(call: Call<MovieResult>?, response: Response<MovieResult>?) {
            response?.isSuccessful.let {
                val resultList = MovieResult(response?.body()?.Search ?: emptyList())

                val fetchedMovies = resultList.Search

                recycler_view.apply {
                    // set a LinearLayoutManager to handle Android
                    // RecyclerView behavior
                    layoutManager = LinearLayoutManager(activity)
                    // set the custom adapter to the RecyclerView
                    adapter = ListAdapter(fetchedMovies) { view, position, type -> //  Navigate to selected item details
                        var bundle = bundleOf("imdbID" to fetchedMovies[position].imdbID)
                        view.findNavController().navigate(R.id.action_searchFragment_to_detailsFragment, bundle)
                        Log.e("MyActivity", "Clicked on item  ${view.id} at position $position with type $type")
                    }
                }
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false)
        //TODO: Should .apply here or assign to property 'recyclerView'?
//        recyclerView = binding.recyclerView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
//            adapter = ListAdapter(movies) { view, position, type -> //  Navigate to selected item details
//                view.findNavController().navigate(R.id.action_searchFragment_to_detailsFragment)
//                Log.e("MyActivity", "Clicked on item  ${view.id} at position $position with type $type")
//            }
        }

        search_field.apply {
            this.isActivated = true
            this.queryHint = "Enter movie name"
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null && query.length > 3) {
                        movieFetcher.getMovies(query, callback)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null && newText.length > 3) {
                        movieFetcher.getMovies(newText, callback)
                    }
                    return false
                }
            })
        }

//        if (context!!.isConnectedToNetwork()) {
//            movieFetcher.getMovies("matrix", callback)
//        } else {
//            AlertDialog.Builder(context).setTitle("No Internet Connection")
//                .setMessage("Please check your internet connection and try again")
//                .setPositiveButton(android.R.string.ok) { _, _ -> }
//                .setIcon(android.R.drawable.ic_dialog_alert).show()
//        }
    }
}
