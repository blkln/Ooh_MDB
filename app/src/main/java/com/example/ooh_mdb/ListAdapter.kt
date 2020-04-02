package com.example.ooh_mdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ListAdapter(private val list: List<Movie>,
                  val itemClickListener: (View, Int, Int) -> Unit)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val movieVH = MovieViewHolder(inflater, parent)
        movieVH.onClick(itemClickListener)
        return movieVH
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

}

//TODO: Should leave here or move to separate class?
class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mYearView: TextView? = null
    private var mIconView: ImageView? = null


    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mYearView = itemView.findViewById(R.id.list_description)
        mIconView = itemView.findViewById(R.id.list_icon)
    }

    fun bind(movie: Movie) {
        mTitleView?.text = movie.Title.orEmpty()
        mYearView?.text = movie.Year.orEmpty()
        Picasso.get().load(movie.Poster).into(mIconView)

    }

}

//  Selected item recognition extension
fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, getAdapterPosition(), getItemViewType())
    }
    return this
}


