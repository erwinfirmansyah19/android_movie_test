package com.example.moviereviewer.model

import io.realm.DynamicRealm
import io.realm.RealmList
import io.realm.RealmMigration

open class Migration : RealmMigration{

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        val schema = realm?.schema
        var curVersion = oldVersion
        println("cur ver" + curVersion)
        if(curVersion == 3.toLong()){
//            println("Migration Doing")
//            schema!!.create("DtbReview")
//                .addField("ReviewLocalID", String::class.java)
//                .addField("ReviewID", String::class.java)
//                .addField("Author", String::class.java)
//                .addField("UserReview", String::class.java)
//                .addField("URL", String::class.java)
//                .addField("MovieID", String::class.java)
//
//            println("Migration Done")
//            curVersion++
//            oldVersion.inc()
        }
    }

}