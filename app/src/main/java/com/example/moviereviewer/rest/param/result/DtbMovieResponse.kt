package com.example.moviereviewer.rest.param.result

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.annotations.PrimaryKey

class DtbMovieResponse {
    @PrimaryKey
    var MovieLocalID : String? = null
    @SerializedName("id")
    var MovieID : String? = null
    @SerializedName("backdrop_path")
    var BackDropPath : String? = null
    @SerializedName("title")
    var Title : String? = null
    @SerializedName("adult")
    var Adult : Boolean? = false
    @SerializedName("original_title")
    var OriginalTitle : String? = null
    @SerializedName("original_language")
    var OriginalLanguage : String? = null
    @SerializedName("poster_path")
    var PosterPath : String? = null
    @SerializedName("vote_average")
    var VoteAverage : String? = null
    @SerializedName("overview")
    var Overview : String? = null
    @SerializedName("release_date")
    var ReleaseDate : String? = null
    @SerializedName("vote_count")
    var VoteCount : String? = null
    @SerializedName("genre_ids")
    var Genre : ArrayList<String>? = null
}