package com.example.ooh_mdb

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.ooh_mdb.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ooh_mdb.MovieResult


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val repoRetriever = MovieFetcher()

    private val callback = object : Callback<MovieResult> {
        override fun onFailure(call: Call<MovieResult>?, t: Throwable?) {
            Log.e("SearchFragment", "Problem calling OMDb API", t)
        }

        override fun onResponse(call: Call<MovieResult>?, response: Response<MovieResult>?) {
            response?.isSuccessful.let {
                val resultList = MovieResult(response?.body()?.Search ?: emptyList())

                val fetchedMovies = resultList.Search

                recyclerView.apply {
                    // set a LinearLayoutManager to handle Android
                    // RecyclerView behavior
                    layoutManager = LinearLayoutManager(activity)
                    // set the custom adapter to the RecyclerView
                    adapter = ListAdapter(fetchedMovies) { view, position, type -> //  Navigate to selected item details
                        view.findNavController().navigate(R.id.action_searchFragment_to_detailsFragment)
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
        recyclerView = binding.recyclerView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
//            adapter = ListAdapter(movies) { view, position, type -> //  Navigate to selected item details
//                view.findNavController().navigate(R.id.action_searchFragment_to_detailsFragment)
//                Log.e("MyActivity", "Clicked on item  ${view.id} at position $position with type $type")
//            }
        }

        if (context!!.isConnectedToNetwork()) {
            repoRetriever.getMovies(callback)
        } else {
            AlertDialog.Builder(context).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }
}
