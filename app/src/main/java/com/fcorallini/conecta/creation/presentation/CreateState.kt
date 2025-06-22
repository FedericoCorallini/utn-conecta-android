package com.fcorallini.conecta.creation.presentation

import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import java.time.LocalDate
import java.time.LocalTime

data class CreateState (
    val date: LocalDate = LocalDate.now(),
    val startTime: LocalTime = LocalTime.now(),
    val endTime: LocalTime = LocalTime.now(),
    val maxStudents: Int = 2,
    val title: String = "",
    val studyPlaceDetails: String = "",
    val studyPlaceLocationName : String = "",
    val studyPlaceLocationId: Long = 0,
    val idSubject: Long = 0,
    val subjectName : String = "",
    val locations : List<StudyPlace> = emptyList(),
    val subjectList : List<Subject> = emptyList(),
    val toastMessage : String? = null,
    val dateErrorMessage : String? = null,
    val timeErrorMessage : String? = null,
    val maxStudentsErrorMessage : String? = null,
    val titleErrorMessage : String? = null,
    val studyPlaceDetailsErrorMessage : String? = null
)
