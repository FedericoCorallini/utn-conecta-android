package com.fcorallini.conecta.core.domain.model

data class Career(
    val id : Long,
    val name : String,
    val curriculums : List<Curriculum>
)
