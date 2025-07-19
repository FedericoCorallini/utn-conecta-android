package com.fcorallini.conecta.core.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Meeting(
    val id : Long,
    val date : LocalDate,
    val startTime : LocalTime,
    val endTime : LocalTime,
    val studentsNumber: Int,
    val maxStudents : Int,
    val title : String,
    val studyPlace : StudyPlace,
    val subject : Subject
)
