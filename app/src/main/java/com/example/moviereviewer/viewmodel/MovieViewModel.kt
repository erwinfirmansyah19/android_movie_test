package com.example.moviereviewer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.moviereviewer.model.action.MovieAction
import com.example.moviereviewer.model.schema.DtbMovie
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults

class MovieViewModel {

    var realm = Realm.getDefaultInstance()
    var movieAction = MovieAction(realm)
    var movieList : ArrayList<DtbMovie>? = null

    fun getAllMovie() : RealmResults<DtbMovie>?{
        var movieList = movieAction.getAllMovie()
        if(movieList != null){
            return movieList
        }else{
            return null
        }
    }

    fun getMovieByGenre(genreID : String) : ArrayList<DtbMovie>?{
        var allMovieRealm = movieAction.getMovieByGenreID(genreID)
        if(allMovieRealm != null){
            println(allMovieRealm!!.size)
            try {
                movieList = ArrayList(allMovieRealm)
            }finally {
                if (realm != null) {
                    realm.close();
                }
            }
            return movieList
        }else{
            println("kosong")
            return null
        }
    }

    fun getMovieByName(title : String) : DtbMovie?{
        var movieData = movieAction.getMovieByTitle(title)
        if(movieData != null){
            return movieData
        }else{
            return null
        }
    }

    fun getMovieByMovieID(movieID : String) : DtbMovie?{
        var movieData = movieAction.getMovieByMovieID(movieID)
        if(movieData != null){
            return movieData
        }else{
            return null
        }
    }

}