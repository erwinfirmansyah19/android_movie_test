package com.example.moviereviewer

import android.app.Application
import com.example.moviereviewer.model.Migration
import io.realm.Realm
import io.realm.RealmConfiguration



class InitApp: Application(){
    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        println("INI APP JALAN")
        val configuration = RealmConfiguration.Builder().name("dbmovie.realm").schemaVersion(4).migration(Migration()).build()
        Realm.setDefaultConfiguration(configuration)
        Realm.getInstance(configuration)
        println("INI APP clear")
    }
}