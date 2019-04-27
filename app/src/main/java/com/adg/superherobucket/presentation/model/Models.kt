package com.adg.superherobucket.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuperHero(
    val id: String,
    val name: String,
    val image: Image,
    val biography: Biography,
    val appearance: Appearance) : Parcelable

@Parcelize
data class Biography(
    val placeOfBirth: String,
    val aliases: List<String?>,
    val firstAppearance: String,
    val publisher: String,
    val alignment: String,
    val fullName: String,
    val alterEgos: String) : Parcelable

@Parcelize
data class Appearance(
    val eyeColor: String,
    val gender: String,
    val race: String,
    val weight: List<String?>,
    val height: List<String?>,
    val hairColor: String) : Parcelable

@Parcelize
data class Image(
    val url: String) : Parcelable

data class SuperHeroDetailItem(
    val type: DetailType,
    val content: String
)

enum class DetailType {
    SP_NAME,
    SP_FULLNAME,
    SP_PLACE_OF_BIRTH,
    SP_GENDER,
    SP_WEIGHT,
    SP_HEIGHT
}
