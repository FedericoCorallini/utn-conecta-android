package com.fcorallini.conecta.profile.domain.repository

import com.fcorallini.conecta.core.domain.model.Career
import com.fcorallini.conecta.core.domain.model.Curriculum

interface ProfileRepository {
    suspend fun getCareers() : Result<List<Career>>
    suspend fun getFollowedCurriculums(studentId: Long): Result<List<Curriculum>>
    suspend fun setNewStudentProfile(studentId: Long, curriculums : Set<Long>, subjects : Set<Long>) : Result<Unit>
}