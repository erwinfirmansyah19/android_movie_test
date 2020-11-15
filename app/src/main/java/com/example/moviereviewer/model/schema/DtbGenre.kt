package com.example.moviereviewer.model.schema

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DtbGenre : RealmObject(){
    @PrimaryKey
    var GenreLocalID : String? = null
    @SerializedName("id")
    var GenreID : String? = null
    @SerializedName("name")
    var GenreName : String? = null
}