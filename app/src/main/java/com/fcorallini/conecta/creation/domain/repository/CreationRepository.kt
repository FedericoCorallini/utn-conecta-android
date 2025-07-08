package com.fcorallini.conecta.creation.domain.repository

import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject

interface CreationRepository {
    suspend fun create(meeting: Meeting, studentId: Long) : Result<Unit>
    suspend fun getLocations() : Result<List<StudyPlace>>
    suspend fun getFollowedSubjects(studentId: Long) : Result<List<Subject>>
}