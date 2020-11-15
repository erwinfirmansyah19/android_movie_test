package com.example.moviereviewer.activity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviereviewer.R
import com.example.moviereviewer.adapter.MovieAdapter
import com.example.moviereviewer.global.Attributes
import com.example.moviereviewer.model.schema.DtbReview
import com.example.moviereviewer.viewmodel.MovieViewModel
import com.example.moviereviewer.viewmodel.ReviewViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.toolbar
import kotlinx.android.synthetic.main.activity_movie_list.*
import java.util.*
import kotlin.concurrent.schedule

class MovieDetailActivity : AppCompatActivity() {

    var movieViewModel = MovieViewModel()
    var reviewViewModel = ReviewViewModel()
    var genreParent : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)
        toolbar_layout.title = "Detail Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        var bundle = intent.extras
        if(bundle != null) {
            var movieID = bundle.getString("movieID")
            genreParent = bundle.getString("genreParent")
            if(movieID!=null){
                var movieData = movieViewModel.getMovieByMovieID(movieID)
//                toolbar_layout.title = movieData?.Title
                Picasso.get().load(Attributes.POSTER_URL + movieData?.PosterPath).into(moviePoster)
                movieTitle.text = movieData?.Title
                movieRating.text = "Rating "+ movieData?.VoteAverage +" of 10"
                releaseDate.text = "Release Date :  "+ movieData?.ReleaseDate
                movieOverview.text = movieData?.Overview
                if(movieData?.Adult == true){
                    movieCategory.text = "Category : 18+"
                }else{
                    movieCategory.text = "Category : All Ages"
                }
                voteCount.text = "Vote Count : "+ movieData?.VoteCount
                language.text = "Language :  "+ movieData?.OriginalLanguage

                Picasso.get().load(Attributes.BACKDROP_URL + movieData?.BackDropPath).into(backdropMovie)

                reviewViewModel.getReview(movieID)!!
                Timer("Sync", false).schedule(3000){
                    setAdapter(movieID)
                }

            }
        }
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putString("genreID", genreParent)
        var intent : Intent = Intent(applicationContext, MovieListActivity::class.java)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        applicationContext.startActivity(intent)
    }

    fun setAdapter(movieID: String){
        var reviewList = reviewViewModel.getReviewByMovieID(movieID)
        if(reviewList!=null){
            println("totalreview "+ reviewList?.size)
        }else{
            println("masih proses ")
        }

    }
}