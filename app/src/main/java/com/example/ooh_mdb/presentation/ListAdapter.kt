package com.example.ooh_mdb.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Movie
import com.example.ooh_mdb.R
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates


class ListAdapter(//private val moviesList: List<Movie>,
                  val itemClickListener: (View, Int) -> Unit
//    val itemClickListener: OnItemClickListener
)
    : RecyclerView.Adapter<MovieViewHolder>() {

    var list: List<Movie> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

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

    fun bind(
        movie: Movie//,
//        itemClickListener: OnItemClickListener
    ) {

        mTitleView?.text = movie.Title.orEmpty()
        mYearView?.text = movie.Year.orEmpty()
        Picasso.get().load(movie.Poster).into(mIconView)

//        itemView.setOnClickListener {
//            itemClickListener.onItemClicked(movie)
//        }

    }

}

//  Selected item recognition extension
fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, getAdapterPosition())
    }
    return this
}

interface OnItemClickListener {
    fun onItemClicked(movie: Movie)
}


