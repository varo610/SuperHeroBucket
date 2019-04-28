package com.adg.superherobucket.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adg.superherobucket.data.db.model.DBSuperHero

@Database(entities = [DBSuperHero::class], version = 1)
abstract class SuperHeroDataBase : RoomDatabase(){

    abstract fun superHeroDao(): SuperHeroDAO

}