package com.fcorallini.conecta.profile.domain.repository

import com.fcorallini.conecta.core.domain.model.Career
import com.fcorallini.conecta.core.domain.model.Curriculum
import com.fcorallini.conecta.core.domain.model.Subject

interface ProfileRepository {
    suspend fun getCareers() : Result<List<Career>>
    suspend fun getFollowedCurriculums(studentId: Long): Result<List<Curriculum>>
    suspend fun setNewStudentProfile(studentId: Long, curriculums : Set<Long>, subjects : Set<Long>) : Result<Unit>
    suspend fun getFollowedSubjectsIds(studentId: Long) : Result<List<Long>>
}
