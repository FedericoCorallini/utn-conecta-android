package com.fcorallini.conecta.core.domain.model

data class StudyPlace(
    val id : Long?,
    val location : String,
    val isVirtual : Boolean,
    val details : String = ""
)
