package com.fcorallini.conecta.creation.presentation

import java.time.LocalDate
import java.time.LocalTime

interface CreateEvent {
    data class DateChanged(val date: LocalDate) : CreateEvent
    data class StartTimeChanged(val startTime: LocalTime) : CreateEvent
    data class EndTimeChanged(val endTime: LocalTime) : CreateEvent
    data class MaxStudentsChanged(val maxStudents: Int) : CreateEvent
    data class TitleChanged(val value: String) : CreateEvent
    data class StudyPlaceDetailsChanged(val value: String) : CreateEvent
    data class StudyPlaceLocationNameChanged(val location: String) : CreateEvent
    data class IdSubjectChanged(val id: Long) : CreateEvent
    data class SubjectNameChanged(val name : String) : CreateEvent
    object Submit : CreateEvent
}