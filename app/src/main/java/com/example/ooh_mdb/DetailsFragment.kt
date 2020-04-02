package com.example.ooh_mdb

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ooh_mdb.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {

    private val movieFetcher = MovieFetcher()

    private val callback = object : Callback<Movie> {
        override fun onFailure(call: Call<Movie>?, t: Throwable?) {
            Log.e("DetailsFragment", "Problem calling OMDb API", t)
        }

        override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
            response?.isSuccessful.let {
                val movie = response?.body()

                Picasso.get().load(movie?.Poster).into(poster)
                title.text = movie?.Title
                genre.text = "Genre: " + movie?.Genre.orEmpty()
                director.text = "Director: " + movie?.Director.orEmpty()
                writer.text = "Writer: " + movie?.Writer.orEmpty()
                actors.text = "Actors: " + movie?.Actors.orEmpty()
                description.text = movie?.Plot

            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imdbID = arguments?.getString("imdbID")
        if (imdbID != null) {
            movieFetcher.getMovieDetails(imdbID, callback)
        }
    }
}
