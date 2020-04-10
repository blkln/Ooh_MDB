package com.example.ooh_mdb.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Movie
import com.example.ooh_mdb.R
import com.squareup.picasso.Picasso


class ListAdapter(private val itemClickListener: (View, Int) -> Unit)
    : RecyclerView.Adapter<MovieViewHolder>() {

    var list: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val movieVH = MovieViewHolder(inflater, parent)
        movieVH.onClick(itemClickListener)
        return movieVH
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: com.example.domain.Movie = list[position]
        holder.bind(movie)
//        holder.bind(movie, itemClickListener)
    }

    override fun getItemCount(): Int = list.size

    fun update(newMovies: List<Movie>) {
        list = newMovies

        notifyDataSetChanged()
    }


}

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var titleView: TextView?
    private var yearView: TextView?
    private var posterView: ImageView?


    init {
        titleView = itemView.findViewById(R.id.listItemTitle)
        yearView = itemView.findViewById(R.id.listItemYear)
        posterView = itemView.findViewById(R.id.listItemImage)
    }

    fun bind( movie: Movie) {

        mTitleView?.text = movie.Title.orEmpty()
        mYearView?.text = movie.Year.orEmpty()
        Picasso.get().load(movie.Poster).into(mIconView)

//        itemView.setOnClickListener {
//            itemClickListener.onItemClicked(movie)
//        }

    }

}



