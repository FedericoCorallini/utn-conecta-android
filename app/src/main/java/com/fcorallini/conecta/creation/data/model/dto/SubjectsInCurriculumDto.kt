package com.fcorallini.conecta.creation.data.model.dto

import com.squareup.moshi.Json

data class SubjectsInCurriculumDto(

    @Json(name = "career_name")
    val careerName: String,

    @Json(name = "curriculum_id")
    val curriculumId: Long,

    @Json(name = "curriculum_name")
    val curriculumName: String,

    val subjects: List<SubjectDto>
)