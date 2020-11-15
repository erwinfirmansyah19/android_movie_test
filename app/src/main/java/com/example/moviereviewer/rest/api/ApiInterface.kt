package com.example.moviereviewer.rest.api

import com.example.moviereviewer.global.Attributes
import com.example.moviereviewer.rest.param.result.GenreResponse
import com.example.moviereviewer.rest.param.result.MovieResponse
import com.example.moviereviewer.rest.param.result.ReviewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiInterface {

    @GET("genre/movie/list?api_key=" + Attributes.API_KEY)
    fun getGenre() : Call<GenreResponse>

    //I make a Dynamic URL for MOVIE LIST
    @GET("list/{list}?api_key=" + Attributes.API_KEY)
    fun getList(@Path(value = "list", encoded = true) list : String) : Call<MovieResponse>

    @GET("movie/{moviesid}/reviews?api_key=" + Attributes.API_KEY)
    fun getReview(@Path(value = "moviesid", encoded = true) movieid : String) : Call<ReviewResponse>
}