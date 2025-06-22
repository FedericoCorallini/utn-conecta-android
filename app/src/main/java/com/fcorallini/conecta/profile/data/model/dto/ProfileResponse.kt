package com.fcorallini.conecta.profile.data.model.dto

import com.squareup.moshi.Json

data class ProfileResponse(
    @Json(name = "subjects_followed")
    val curriculums: List<ProfileCurriculumDto>
)
