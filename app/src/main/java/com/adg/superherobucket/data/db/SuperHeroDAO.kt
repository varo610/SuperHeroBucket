package com.adg.superherobucket.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.adg.superherobucket.data.db.model.DBSuperHero

@Dao

interface SuperHeroDAO {

    @Query("SELECT * from ${DBNames.dbName}")
    suspend fun getAll(): List<DBSuperHero>

    @Insert(onConflict = REPLACE)
    suspend fun insert(dbSuperHero: DBSuperHero)

    @Delete
    suspend fun delete(dbSuperHero: DBSuperHero)

}