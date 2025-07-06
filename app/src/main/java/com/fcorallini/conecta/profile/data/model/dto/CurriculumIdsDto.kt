package com.fcorallini.conecta.profile.data.model.dto

import com.squareup.moshi.Json

data class CurriculumIdsDto(
    @Json(name = "curriculum_id")
    val curriculumId : Long,
    @Json(name = "subjects_id")
    val subjectId : List<Long>
)
