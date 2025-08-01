package com.fcorallini.conecta.core.data.remote.dto

import com.fcorallini.conecta.core.domain.model.*
import java.time.LocalDate
import java.time.LocalTime

fun MeetingDto.toDomain(): Meeting {
    val dateObj = LocalDate.of(
        date.getOrNull(0) ?: 2000,
        date.getOrNull(1) ?: 1,
        date.getOrNull(2) ?: 1
    )

    val startTimeObj = LocalTime.of(
        start_time.getOrNull(0) ?: 0,
        start_time.getOrNull(1) ?: 0,
        start_time.getOrNull(2) ?: 0,
        start_time.getOrNull(3) ?: 0
    )

    val endTimeObj = LocalTime.of(
        end_time.getOrNull(0) ?: 0,
        end_time.getOrNull(1) ?: 0,
        end_time.getOrNull(2) ?: 0,
        end_time.getOrNull(3) ?: 0
    )

    return Meeting(
        id = id,
        date = dateObj,
        startTime = startTimeObj,
        endTime = endTimeObj,
        studentsNumber = students_number,
        maxStudents = max_students,
        title = title,
        studyPlace = StudyPlace(
            id=-1,
            isVirtual = study_place_is_virtual,
            location = study_place_location_name,
            details = study_place_details
        ),
        subject = Subject(id=-1, name = subject_name)
    )
}

