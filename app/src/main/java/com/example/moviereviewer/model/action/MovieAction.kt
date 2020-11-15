package com.example.moviereviewer.model.action

import com.example.moviereviewer.model.schema.DtbGenre
import com.example.moviereviewer.model.schema.DtbMovie
import com.example.moviereviewer.model.schema.DtbReview
import com.example.moviereviewer.rest.param.result.DtbMovieResponse
import com.google.gson.Gson
import io.realm.Case
import io.realm.Realm
import io.realm.RealmResults
import java.util.*
import kotlin.collections.ArrayList

class MovieAction(realm : Realm) {
    var realm = realm

    fun getAllMovie(): RealmResults<DtbMovie>? {
        return realm.where(DtbMovie::class.java).findAll()
    }

    fun getMovieByGenreID(genreID : String) : RealmResults<DtbMovie>?{
        return realm.where(DtbMovie::class.java).like("GenreString", "*" + genreID + "*", Case.INSENSITIVE).findAll()
    }

    fun getMovieByMovieID(movieID : String) : DtbMovie? {
        return realm.where(DtbMovie::class.java).equalTo("MovieID", movieID).findFirst()
    }

    fun getMovieByTitle(title : String) : DtbMovie? {
        return realm.where(DtbMovie::class.java).equalTo("Title", title).findFirst()
    }

    fun getReviewByMovieID(movieID : String) : RealmResults<DtbReview>? {
        return realm.where(DtbReview::class.java).equalTo("MovieID", movieID).findAll()
    }

    fun saveReviews(data: ArrayList<DtbReview>, movieID: String) {
        realm.executeTransaction {
            for (i in data) {
                val search =
                    it.where(DtbReview::class.java).equalTo("ReviewID", i.ReviewID).findFirst()
                if (search != null) {
                    search.Author = i.Author
                    search.MovieID = movieID
                    search.UserReview = i.UserReview
                    search.MovieID = i.URL
                    println("Author " + i.Author)
                } else {
                    val newData = it.createObject(DtbReview::class.java, UUID.randomUUID().toString())
                    newData.ReviewID = i.ReviewID
                    newData.Author = i.Author
                    newData.MovieID = movieID
                    newData.UserReview = i.UserReview
                    newData.MovieID = i.URL
                    println("Author " + i.Author)
                }
            }
        }
    }

    fun serverSync(data: ArrayList<DtbMovieResponse>) {
        realm.executeTransaction {
            for (i in data) {
                val search =
                    it.where(DtbMovie::class.java).equalTo("MovieID", i.MovieID).findFirst()
                if (search != null) {
                    search.OriginalTitle = i.OriginalTitle
                    search.BackDropPath = i.BackDropPath
                    search.Title = i.Title
                    search.Adult = i.Adult
                    search.OriginalLanguage = i.OriginalLanguage
                    search.PosterPath = i.PosterPath
                    search.VoteAverage = i.VoteAverage
                    search.VoteCount = i.VoteCount
                    search.Overview = i.Overview
                    search.ReleaseDate = i.ReleaseDate
                    search.OriginalTitle = i.OriginalTitle
                    var totalGenreIncude = i.Genre!!.size
                    var genreString : String? = ""
                    if(totalGenreIncude > 0){
                        for(j in 0 until totalGenreIncude){
                            if(j == 0)
                                genreString = i.Genre!!.get(j)
                            else
                                genreString = genreString + ", " + i.Genre!!.get(j)
                        }
                    }else{
                        genreString = ""
                    }
                    search.GenreString = genreString
                } else {
                    val newData = it.createObject(DtbMovie::class.java, UUID.randomUUID().toString())
                    newData.MovieID = i.MovieID
                    newData.OriginalTitle = i.OriginalTitle
                    newData.BackDropPath = i.BackDropPath
                    newData.Title = i.Title
                    newData.Adult = i.Adult
                    newData.OriginalLanguage = i.OriginalLanguage
                    newData.PosterPath = i.PosterPath
                    newData.VoteAverage = i.VoteAverage
                    newData.VoteCount = i.VoteCount
                    newData.Overview = i.Overview
                    newData.ReleaseDate = i.ReleaseDate
                    newData.OriginalTitle = i.OriginalTitle
                    var totalGenreIncude = i.Genre!!.size
                    var genreString : String? = ""
                    if(totalGenreIncude > 0){
                        for(j in 0 until totalGenreIncude){
                            if(j == 0)
                                genreString = i.Genre!!.get(j)
                            else
                                genreString = genreString + ", " + i.Genre!!.get(j)
                        }
                    }else{
                        genreString = ""
                    }
                    newData.GenreString = genreString
                }
            }
        }
    }
}