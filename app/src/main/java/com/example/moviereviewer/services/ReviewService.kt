package com.example.moviereviewer.services

import android.app.IntentService
import android.content.Intent
import android.widget.Toast
import com.example.moviereviewer.model.action.GenreAction
import com.example.moviereviewer.model.action.MovieAction
import com.example.moviereviewer.rest.api.ApiInterface
import com.example.moviereviewer.rest.param.result.GenreResponse
import com.example.moviereviewer.rest.param.result.MovieResponse
import com.google.gson.Gson
import id.hellobill.hellobilltrackingsales.rest.api.ApiClient
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewService : IntentService("getterservice"){

    val apiService = ApiClient.client.create(ApiInterface::class.java)
    var realm = Realm.getDefaultInstance()
    var genreAction = GenreAction(realm)
    var movieAction = MovieAction(realm)
    override fun onHandleIntent(intent: Intent?) {
        syncMovie()
    }

    fun syncGenre(){
        var call = apiService.getGenre()
        call.enqueue(object : Callback<GenreResponse>{
            override fun onFailure(call: Call<GenreResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Sync Genre Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<GenreResponse>?,response: Response<GenreResponse>?) {
                if(response != null){
                    if(response.body() != null){
                        var Genres = response.body().getResults()
                        if(Genres != null)
                            genreAction.serverSync(Genres)
                    }else{
                        Toast.makeText(applicationContext, "Sync Genre : Response 500", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext, "Sync Genre : Response 500", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    fun syncMovie() {
        for (i in 1 until 10) {
            var call = apiService.getList(i.toString())
//        println(Gson().toJson(call))
            call.enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "Sync Movie Failed", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<MovieResponse>?,
                    response: Response<MovieResponse>?
                ) {
                    if (response != null) {
                        if (response.body() != null) {
                            var Movies = response.body().getResults()
                            if (Movies != null) {
                                movieAction.serverSync(Movies)
                            }
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Sync Movie : Response 500",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Sync Movie : Response 500",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })
        }
    }
}