package com.example.moviereviewer.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviereviewer.R
import com.example.moviereviewer.services.GetterService
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        var intentService : Intent = Intent(applicationContext, GetterService::class.java)
        startService(intentService)
        Timer("SplashScreen", false).schedule(3000){
            var intent : Intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}