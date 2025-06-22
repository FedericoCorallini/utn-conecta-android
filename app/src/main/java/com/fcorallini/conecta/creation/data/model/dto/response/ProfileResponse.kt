package com.fcorallini.conecta.creation.data.model.dto.response

import com.fcorallini.conecta.creation.data.model.dto.SubjectsInCurriculumDto
import com.squareup.moshi.Json

data class ProfileResponse(

    val email: String,

    @Json(name = "last_name")
    val lastName: String,

    val name: String,

    @Json(name = "subjects_followed")
    val subjectsFollowed: List<SubjectsInCurriculumDto>
)