package com.example.moviereviewer.activity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviereviewer.R
import com.example.moviereviewer.adapter.MovieAdapter
import com.example.moviereviewer.adapter.ReviewAdapter
import com.example.moviereviewer.global.Attributes
import com.example.moviereviewer.model.schema.DtbReview
import com.example.moviereviewer.rest.api.ApiInterface
import com.example.moviereviewer.rest.param.result.ReviewResponse
import com.example.moviereviewer.viewmodel.MovieViewModel
import com.example.moviereviewer.viewmodel.ReviewViewModel
import com.squareup.picasso.Picasso
import id.hellobill.hellobilltrackingsales.rest.api.ApiClient
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.toolbar
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.activity_movie_review.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class MovieReviewActivity : AppCompatActivity() {
    val apiService = ApiClient.client.create(ApiInterface::class.java)
    var genreParent : String? = null
    var movieID : String? = ""
    var reviewList : ArrayList<DtbReview>? = null
    var reviewAdapter : ReviewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_review)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Review Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        var bundle = intent.extras
        if(bundle != null) {
            movieID = bundle.getString("movieID")
            if(movieID!=null && movieID!="") {

                //SANGAT TERPAKSA MELAKUKAN CALL API DI DALAM ACTIVITY
                var call = apiService.getReview(movieID!!)
                println(call?.request())
                call.enqueue(object : Callback<ReviewResponse> {
                    override fun onFailure(call: Call<ReviewResponse>?, t: Throwable?) {
                        println("Response 500")
                    }

                    override fun onResponse(
                        call: Call<ReviewResponse>?,
                        response: Response<ReviewResponse>?
                    ) {
                        if (response != null) {
                            if (response.body() != null) {
                                response.body().getResults()
                                var movieID = response.body().MovieID
                                println("body not null")
                                if (response.body().getResults() != null && movieID != null) {
                                    reviewList = response.body().getResults()
                                    reviewAdapter = ReviewAdapter(reviewList!!, applicationContext)
                                    rvReview.adapter = reviewAdapter
                                    rvReview.layoutManager = LinearLayoutManager(applicationContext)
                                    rvReview.addItemDecoration(DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL))
                                }
                            } else {
                                println("body Null")
                            }
                        } else {
                            println("body Null")
                        }
                    }
                })
            }
        }


    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putString("movieID", movieID)
        var intent : Intent = Intent(applicationContext, MovieDetailActivity::class.java)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        applicationContext.startActivity(intent)
        }
    }