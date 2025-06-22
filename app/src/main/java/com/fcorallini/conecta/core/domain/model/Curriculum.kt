package com.fcorallini.conecta.core.domain.model

data class Curriculum(
    val id : Long,
    val name : String,
    val subjects : List<Subject>
)
