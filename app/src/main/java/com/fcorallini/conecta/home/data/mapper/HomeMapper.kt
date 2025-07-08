package com.fcorallini.conecta.home.data.mapper

import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.home.data.model.dto.CalendarResponse
import com.fcorallini.conecta.home.data.model.dto.MeetingResponse
import java.time.LocalDate
import java.time.LocalTime

fun MeetingResponse.toDomain() : Meeting {
    return Meeting(
        id = id,
        date = LocalDate.of(date[0], date[1], date[2]),
        startTime = LocalTime.of(startTime[0], startTime[1]),
        endTime = LocalTime.of(endTime[0], endTime[1]),
        maxStudents = maxStudents ?: 0,
        title = title ?: "",
        studyPlace = StudyPlace(
            id = null,
            location = studyPlaceLocationName ?: "",
            isVirtual = studyPlaceIsVirtual ?: false
        ),
        subject = Subject(
            id = 0,
            name = subjectName ?: ""
        )
    )
}