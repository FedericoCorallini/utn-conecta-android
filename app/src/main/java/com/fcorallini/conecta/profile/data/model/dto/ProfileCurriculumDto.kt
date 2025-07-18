package com.fcorallini.conecta.profile.data.model.dto

import com.fcorallini.conecta.creation.data.model.dto.SubjectDto
import com.squareup.moshi.Json

data class ProfileCurriculumDto(
    @Json(name = "curriculum_id")
    val curriculumId: Long,

    @Json(name = "curriculum_name")
    val curriculumName: String,

    @Json(name = "career_name")
    val careerName: String,

    val subjects: List<SubjectDto>
)
