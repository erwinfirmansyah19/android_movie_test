package com.example.moviereviewer.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.moviereviewer.activity.MovieDetailActivity
import com.example.moviereviewer.model.action.MovieAction
import com.example.moviereviewer.model.schema.DtbMovie
import com.example.moviereviewer.model.schema.DtbReview
import com.example.moviereviewer.rest.api.ApiInterface
import com.example.moviereviewer.rest.param.result.GenreResponse
import com.example.moviereviewer.rest.param.result.ReviewResponse
import id.hellobill.hellobilltrackingsales.rest.api.ApiClient
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class ReviewViewModel {
    val apiService = ApiClient.client.create(ApiInterface::class.java)
    var realm = Realm.getDefaultInstance()
    var movieAction = MovieAction(realm)
    var reviewList : ArrayList<DtbReview>? = null

    fun getReview(movieID : String){
        var call = apiService.getReview(movieID)
        println(call?.request())
        call.enqueue(object : Callback<ReviewResponse> {
            override fun onFailure(call: Call<ReviewResponse>?, t: Throwable?) {
                println("Response 500")
            }

            override fun onResponse(call: Call<ReviewResponse>?, response: Response<ReviewResponse>?) {
                if(response != null){
                    if(response.body() != null){
                        reviewList = response.body().getResults()
                            var movieID = response.body().MovieID
                        println("body not null")
                            if(reviewList!=null && movieID!=null){
                                println("save " + movieID)
                                movieAction.saveReviews(reviewList!!, movieID!!)
                                realm.close();
                            }
                    }else{
                        println("body Null")
                    }
                }else{
                    println("body Null")
                }
            }
        })
    }

    fun getReviewByMovieID(movieID : String) : ArrayList<DtbReview>?{
        var new_realm = Realm.getDefaultInstance()
        var new_movieAction = MovieAction(new_realm)
        var allReviewRealm = new_movieAction.getReviewByMovieID(movieID)
        println("MOVIE ID " + movieID)
        if(allReviewRealm != null){
            println("total review " + allReviewRealm!!.size)
            try {
                reviewList = ArrayList(allReviewRealm)
            }finally {
                if (new_realm != null) {
                    new_realm.close();
                }
            }
            return reviewList
        }else{
            println("kosong")
            return null
        }
    }

}