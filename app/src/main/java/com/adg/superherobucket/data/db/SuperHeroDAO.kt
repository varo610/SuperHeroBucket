package com.adg.superherobucket.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.adg.superherobucket.data.db.model.DBSuperHero
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface SuperHeroDAO {

    @Query("SELECT * from ${DBNames.dbName}")
    fun getAll(): Maybe<List<DBSuperHero>>

    @Insert(onConflict = REPLACE)
    fun insert(dbSuperHero: DBSuperHero): Completable

    @Delete
    fun delete(dbSuperHero: DBSuperHero): Completable

}