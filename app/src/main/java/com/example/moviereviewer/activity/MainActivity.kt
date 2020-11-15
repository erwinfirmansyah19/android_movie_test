package com.example.moviereviewer.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviereviewer.R
import com.example.moviereviewer.adapter.GenreAdapter
import com.example.moviereviewer.model.schema.DtbGenre
import com.example.moviereviewer.viewmodel.GenreViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var genreAdapter : GenreAdapter?= null
    var genreViewModel = GenreViewModel()
    var genreList : ArrayList<DtbGenre>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Movie Reviewer (Genre)"
        genreList = genreViewModel.getAllGenre()
        if(genreList!=null){
            genreAdapter = GenreAdapter(genreList!!, applicationContext)
        }
        rvGenre.adapter = genreAdapter
        rvGenre.layoutManager = LinearLayoutManager(applicationContext)
        rvGenre.addItemDecoration(DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL)
        )
    }


}