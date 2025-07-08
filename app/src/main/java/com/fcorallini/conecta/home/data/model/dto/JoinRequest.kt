package com.fcorallini.conecta.home.data.model.dto

import com.squareup.moshi.Json

data class JoinRequest(
    @Json(name = "meeting_id")
    val meetingId : Long,
    val email : String
)
