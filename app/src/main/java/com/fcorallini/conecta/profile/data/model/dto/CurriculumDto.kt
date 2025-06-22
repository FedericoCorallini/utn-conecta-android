package com.fcorallini.conecta.profile.data.model.dto

data class CurriculumDto(
    val id : Long?,
    val name : String,
    val subjects : List<SubjectDto>
)
