package com.adg.superherobucket.presentation.main

import com.adg.superherobucket.domain.model.toApperance
import com.adg.superherobucket.domain.model.toBiography
import com.adg.superherobucket.presentation.model.Appearance
import com.adg.superherobucket.presentation.model.Biography
import com.adg.superherobucket.presentation.model.Image
import com.adg.superherobucket.presentation.model.SuperHero

val superHeroImage = Image(url = "url")

val superHeroBio = Biography(
    placeOfBirth = "placeOfBirth",
    firstAppearance = "firstAppearance",
    publisher = "publisher",
    alignment = "alignment",
    fullName = "fullName",
    alterEgos = "alterEgos"
)

val superHeroAppearance = Appearance(
    eyeColor = "eyeColor",
    gender = "gender",
    race = "race",
    weight = "weight",
    height = "height",
    hairColor = "hairColor"
)

val superHero = SuperHero(
    id = "id",
    favorite = false,
    name = "name",
    image = superHeroImage,
    biography = superHeroBio,
    appearance = superHeroAppearance

)

