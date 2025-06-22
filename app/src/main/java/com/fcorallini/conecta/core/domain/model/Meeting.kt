package com.fcorallini.conecta.core.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Meeting(
    val date : LocalDate,
    val startTime : LocalTime,
    val endTime : LocalTime,
    val maxStudents : Int,
    val title : String,
    val studyPlace : StudyPlace,
    val subject : Subject
)
