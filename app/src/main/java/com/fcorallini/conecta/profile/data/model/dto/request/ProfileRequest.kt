package com.fcorallini.conecta.profile.data.model.dto.request

import com.fcorallini.conecta.profile.data.model.dto.CurriculumIdsDto
import com.squareup.moshi.Json

data class ProfileRequest(
    @Json(name = "subjects_followed")
    val curriculums: List<CurriculumIdsDto>
)
