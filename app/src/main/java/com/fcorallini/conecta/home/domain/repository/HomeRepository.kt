package com.fcorallini.conecta.home.domain.repository

import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject

interface HomeRepository {
    suspend fun getMeetingsForStudent(studentId : Long) : Result<List<Meeting>>
    suspend fun getCalendarMeetings(studentId: Long) : Result<List<Meeting>>
    suspend fun unlinkMeeting(studentId: Long, meetingId : Long) : Result<Unit>
    suspend fun joinMeeting(studentEmail: String, meetingId: Long) : Result<Unit>
}