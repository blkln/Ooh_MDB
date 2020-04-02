package com.example.ooh_mdb

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


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val movies = listOf(

        Movie("Harry Potter and the Sorcerer's Stone", 2001),
        Movie("Harry Potter and the Chamber of Secrets", 2002),
        Movie("Harry Potter and the Prisoner of Azkaban", 2004),
        Movie("Harry Potter and the Goblet of Fire", 2005),
        Movie("Harry Potter and the Order of the Phoenix", 2007),
        Movie("Harry Potter and the Half-Blood Prince", 2009),
        Movie("Harry Potter and the Deathly Hallows: Part 1", 2010),
        Movie("Harry Potter and the Deathly Hallows: Part 2", 2011)

    )

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
            adapter = ListAdapter(movies) { view, position, type -> //  Navigate to selected item details
                view.findNavController().navigate(R.id.action_searchFragment_to_detailsFragment)
                Log.e("MyActivity", "Clicked on item  ${view.id} at position $position with type $type")
            }
        }
    }
}
