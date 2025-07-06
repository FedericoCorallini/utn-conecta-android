package com.fcorallini.conecta.profile.data.model.dto.response

import com.fcorallini.conecta.profile.data.model.dto.ProfileCurriculumDto
import com.squareup.moshi.Json

data class ProfileResponse(
    @Json(name = "subjects_followed")
    val curriculums: List<ProfileCurriculumDto>
)
