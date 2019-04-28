package com.adg.superherobucket.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adg.superherobucket.data.db.DBNames

@Entity(tableName = DBNames.dbName)
data class DBSuperHero(
    @PrimaryKey
    @ColumnInfo(name = DBNames.colId) val id : String,
    @ColumnInfo(name = DBNames.colName) val name: String,
    @ColumnInfo(name = DBNames.colPlaceOfBirth) val placeOfBirth: String,
    @ColumnInfo(name = DBNames.colFirstAppearance) val firstAppearance: String,
    @ColumnInfo(name = DBNames.colPublisher) val publisher: String,
    @ColumnInfo(name = DBNames.colAlignment) val alignment: String,
    @ColumnInfo(name = DBNames.colFullName) val fullName: String,
    @ColumnInfo(name = DBNames.colAlterEgos) val alterEgos: String,
    @ColumnInfo(name = DBNames.colEyeColor) val eyeColor: String,
    @ColumnInfo(name = DBNames.colGender) val gender: String,
    @ColumnInfo(name = DBNames.colRace) val race: String,
    @ColumnInfo(name = DBNames.colWeight) val weight: String,
    @ColumnInfo(name = DBNames.colHeight) val height: String,
    @ColumnInfo(name = DBNames.colHairColor) val hairColor: String,
    @ColumnInfo(name = DBNames.colUrl) val url: String
)