package com.example.ooh_mdb.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ooh_mdb.R
import com.example.ooh_mdb.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

/**
 * A movie details ragment.
 */
class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the viewModel
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        // Set the viewModel for dataBinding
        binding.detailsViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding.
        binding.setLifecycleOwner(this)

        // Observing viewModel to load poster
        viewModel.movie.observe(viewLifecycleOwner, Observer { movie ->

            movie.Poster.let {
                if (!movie.Poster!!.isEmpty()) {
                    loadPoster(movie.Poster!!)
                }
            }

        })

        val imdbID = arguments?.getString("imdbID")
        if (imdbID != null && !imdbID.isEmpty()) {
            viewModel.loadMovieDetails(imdbID)
        }
    }

    fun loadPoster(uri: String) {

        Picasso.get()
            .load(uri)
            .error(R.drawable.reel)
            .placeholder(R.drawable.reel)
            .into(binding.poster)
    }

}
