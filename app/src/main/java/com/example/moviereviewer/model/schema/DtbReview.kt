package com.example.moviereviewer.model.schema

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DtbReview : RealmObject(){
    @PrimaryKey
    var ReviewLocalID : String? = null
    @SerializedName("id")
    var ReviewID : String? = null
    @SerializedName("author")
    var Author : String? = null
    @SerializedName("content")
    var UserReview : String? = null
    @SerializedName("url")
    var URL : String? = null
    var MovieID : String? = null
}