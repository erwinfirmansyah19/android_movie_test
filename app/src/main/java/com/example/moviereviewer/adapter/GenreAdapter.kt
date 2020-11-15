package com.example.moviereviewer.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereviewer.R
import com.example.moviereviewer.activity.MovieListActivity
import com.example.moviereviewer.model.schema.DtbGenre
import kotlinx.android.synthetic.main.genre_list_layout.view.*

class GenreAdapter (val items: ArrayList<DtbGenre>, val context: Context): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(LayoutInflater.from(context).inflate(R.layout.genre_list_layout, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class GenreViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val genreName = itemView.genreName
        val genreCard = itemView.genreCard
    }

    override fun onBindViewHolder(p0: GenreViewHolder, p1: Int) {
        p0?.genreName?.setText(items.get(p1).GenreName)
        p0?.genreCard?.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("genreID", items.get(p1).GenreID)
            var intent : Intent = Intent(context, MovieListActivity::class.java)
            intent.putExtras(bundle)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

}