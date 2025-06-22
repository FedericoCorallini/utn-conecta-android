package com.fcorallini.conecta.profile.data.model.dto


data class CareerDto(
    val id : Long,
    val name : String,
    val curriculums: List<CurriculumDto>
)
