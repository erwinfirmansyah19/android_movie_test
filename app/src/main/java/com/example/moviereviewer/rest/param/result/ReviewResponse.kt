package com.example.moviereviewer.rest.param.result

import com.example.moviereviewer.model.schema.DtbGenre
import com.example.moviereviewer.model.schema.DtbReview
import com.google.gson.annotations.SerializedName

class ReviewResponse  {
    @SerializedName("id")
    var MovieID : String? = null
    @SerializedName("page")
    var page : String? = null
    @SerializedName("results")
    var Review : ArrayList<DtbReview>? = null

    fun getResults() : ArrayList<DtbReview>?{
        if(Review != null)
            return Review!!
        else
            return null
    }
}