package com.adg.superherobucket.data.db

import com.adg.superherobucket.data.db.model.DBSuperHero
import io.reactivex.Completable
import io.reactivex.Maybe


interface DBDatasource {

    fun getAll(): Maybe<List<DBSuperHero>>

    fun addFavorite(dbSuperHero: DBSuperHero): Completable

    fun removeFavorite(dbSuperHero: DBSuperHero): Completable

}