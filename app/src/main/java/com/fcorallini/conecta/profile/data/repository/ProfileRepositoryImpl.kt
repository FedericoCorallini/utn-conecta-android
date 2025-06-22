package com.fcorallini.conecta.profile.data.repository

import com.fcorallini.conecta.core.data.util.resultOf
import com.fcorallini.conecta.core.domain.model.Career
import com.fcorallini.conecta.core.domain.model.Curriculum
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.creation.data.mapper.toDomain
import com.fcorallini.conecta.creation.data.model.dto.SubjectDto
import com.fcorallini.conecta.profile.data.mapper.toDomain
import com.fcorallini.conecta.profile.data.remote.ProfileApi
import com.fcorallini.conecta.profile.domain.repository.ProfileRepository
import retrofit2.HttpException
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api : ProfileApi
): ProfileRepository {

    override suspend fun getCareers(): Result<List<Career>> {
        return resultOf {
            api.getCareerList().map {
                it.toDomain()
            }
        }
    }

    override suspend fun getFollowedCurriculums(studentId: Long): Result<List<Curriculum>> {
        return resultOf {
            api.getStudentProfile(studentId.toString()).curriculums.map {
                it.toDomain()
            }
        }
    }
}