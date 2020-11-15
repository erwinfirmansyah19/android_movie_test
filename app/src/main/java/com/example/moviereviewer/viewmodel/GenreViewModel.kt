package com.example.moviereviewer.viewmodel

import android.provider.Contacts.People
import com.example.moviereviewer.model.action.GenreAction
import com.example.moviereviewer.model.schema.DtbGenre
import io.realm.Realm


class GenreViewModel {

    var realm = Realm.getDefaultInstance()
    var genreAction = GenreAction(realm)
    var genreList : ArrayList<DtbGenre>? = null

    fun getAllGenre() : ArrayList<DtbGenre>?{
        var allGenreList = genreAction.getAllGenre()
        if(allGenreList != null){
            try {
                    genreList = ArrayList(allGenreList)
            }finally {
                if (realm != null) {
                    realm.close();
                }
            }
            return genreList
        }else{
            return null
        }
    }

    fun getGenreByGenreName(genreName : String) : DtbGenre?{
        var genreData = genreAction.getGenreByName(genreName)
        if(genreData != null){
            return genreData
        }else{
            return null
        }
    }

    fun getGenreByGenreID(genreID : String) : DtbGenre?{
        var genreData = genreAction.getGenreByID(genreID)
        if(genreData != null){
            return genreData
        }else{
            return null
        }
    }

}