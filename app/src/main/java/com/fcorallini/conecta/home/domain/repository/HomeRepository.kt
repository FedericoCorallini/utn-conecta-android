package com.fcorallini.conecta.home.domain.repository

import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject

interface HomeRepository {
    suspend fun getMeetingsForStudent(studentId : Long) : Result<List<Meeting>>
}