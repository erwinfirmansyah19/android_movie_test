package com.example.moviereviewer.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereviewer.R
import com.example.moviereviewer.activity.MovieDetailActivity
import com.example.moviereviewer.activity.MovieListActivity
import com.example.moviereviewer.global.Attributes
import com.example.moviereviewer.model.schema.DtbMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_layout.view.*

class MovieAdapter (val items: ArrayList<DtbMovie>, val context: Context, val genreParent : String): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_layout, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MovieViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val movieTitle = itemView.movieTitle
        val movieRating = itemView.movieRating
        val movieRelease = itemView.releaseDate
        val movieOverview = itemView.movieOverview
        val moviePoster = itemView.moviePoster
        val movieDetailCard = itemView.movieDetailCard

    }

    override fun onBindViewHolder(p0: MovieViewHolder, p1: Int) {
        p0?.movieTitle?.setText(items.get(p1).Title)
        p0?.movieRating?.setText("Rating " + items.get(p1).VoteAverage +"/10")
        p0?.movieRelease?.setText("Release " + items.get(p1).ReleaseDate)
        p0?.movieOverview?.setText(items.get(p1).Overview)
        Picasso.get().load(Attributes.POSTER_URL + items.get(p1).PosterPath).into(p0?.moviePoster)

        p0?.movieDetailCard?.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("movieID", items.get(p1).MovieID)
            bundle.putString("genreParent", genreParent)
            var intent : Intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtras(bundle)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

}