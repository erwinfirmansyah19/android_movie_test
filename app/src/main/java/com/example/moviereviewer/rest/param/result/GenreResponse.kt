package com.example.moviereviewer.rest.param.result

import com.example.moviereviewer.model.schema.DtbGenre
import com.google.gson.annotations.SerializedName

class GenreResponse  {
    @SerializedName("genres")
    var Genres : ArrayList<DtbGenre>? = null

    fun getResults() : ArrayList<DtbGenre>?{
        if(Genres != null)
            return Genres!!
        else
            return null
    }
}