package com.fcorallini.conecta.creation.data.model.dto

import com.squareup.moshi.Json

data class StudyPlaceDto(

    val details : String,

    @Json(name = "location_id")
    val locationId : Long
)
