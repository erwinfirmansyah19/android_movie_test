package com.example.moviereviewer.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviereviewer.R
import com.example.moviereviewer.adapter.MovieAdapter
import com.example.moviereviewer.viewmodel.GenreViewModel
import com.example.moviereviewer.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.activity_movie_list.toolbar

class MovieListActivity : AppCompatActivity() {

    var genreViewModel = GenreViewModel()
    var movieViewModel = MovieViewModel()
    var movieAdapter : MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Movie list"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        var bundle = intent.extras
        if(bundle != null) {
            var genreID = bundle.getString("genreID")
            if(genreID!=null){
                var genreData = genreViewModel.getGenreByGenreID(genreID)
                supportActionBar?.title = "Movie " + genreData!!.GenreName

                var movieList = movieViewModel.getMovieByGenre(genreID)
                if(movieList!=null){
                    movieAdapter = MovieAdapter(movieList!!, applicationContext, genreID)
                    rvMovies.adapter = movieAdapter
                    rvMovies.layoutManager = LinearLayoutManager(applicationContext)
                    rvMovies.addItemDecoration(DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL))
                }
            }
        }
    }

    override fun onBackPressed() {
        var intent : Intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        applicationContext.startActivity(intent)
    }
}