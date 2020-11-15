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
import com.example.moviereviewer.model.schema.DtbReview
import kotlinx.android.synthetic.main.genre_list_layout.view.*
import kotlinx.android.synthetic.main.genre_list_layout.view.genreCard
import kotlinx.android.synthetic.main.review_layout.view.*

class ReviewAdapter (val items: ArrayList<DtbReview>, val context: Context): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(LayoutInflater.from(context).inflate(R.layout.review_layout, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ReviewViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val authors = itemView.authors
        val comment = itemView.review
    }

    override fun onBindViewHolder(p0: ReviewViewHolder, p1: Int) {
        p0?.authors?.setText("Author : " + items.get(p1).Author)
        p0?.comment?.setText("\"" + items.get(p1).UserReview + "\"")

    }

}