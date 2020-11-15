package com.example.moviereviewer.rest.param.result

import com.example.moviereviewer.model.schema.DtbGenre
import com.example.moviereviewer.model.schema.DtbMovie
import com.google.gson.annotations.SerializedName

class MovieResponse  {
    @SerializedName("id")
    var ListID : String? = null
    @SerializedName("name")
    var ListName : String? = null
    @SerializedName("created_by")
    var CreatedBy : String? = null
    @SerializedName("description")
    var Desc : String? = null
    @SerializedName("favorite_count")
    var FavoriteCount : String? = null
    @SerializedName("item_count")
    var TotalItem : String? = null
    @SerializedName("poster_path")
    var PosterListPath : String? = null
    @SerializedName("items")
    var ListMovie : ArrayList<DtbMovieResponse>? = null
    fun getResults() : ArrayList<DtbMovieResponse>?{
        if(ListMovie != null)
            return ListMovie!!
        else
            return null
    }
}