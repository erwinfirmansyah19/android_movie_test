package com.example.moviereviewer.model.action

import com.example.moviereviewer.model.schema.DtbGenre
import io.realm.Realm
import io.realm.RealmResults
import java.util.*
import kotlin.collections.ArrayList

class GenreAction(realm : Realm) {
    var realm = realm

    fun getAllGenre() : RealmResults<DtbGenre>? {
        return realm.where(DtbGenre::class.java).findAll()
    }

    fun getGenreByName(genreName : String) : DtbGenre? {
        return realm.where(DtbGenre::class.java).equalTo("GenreName", genreName).findFirst()
    }

    fun getGenreByID(genreID : String) : DtbGenre? {
        return realm.where(DtbGenre::class.java).equalTo("GenreID", genreID).findFirst()
    }

    fun serverSync(data : ArrayList<DtbGenre>){
        realm.executeTransaction {
            for(i in data){
                val search = it.where(DtbGenre::class.java).equalTo("GenreID", i.GenreID).findFirst()
                if(search != null){
                    search.GenreID = i.GenreID
                    search.GenreName = i.GenreName
                }else{
                    val newData = it.createObject(DtbGenre::class.java, UUID.randomUUID().toString())
                    newData.GenreID = i.GenreID
                    newData.GenreName = i.GenreName
                }
            }
        }
    }
}