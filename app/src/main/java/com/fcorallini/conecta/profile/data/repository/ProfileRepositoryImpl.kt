package com.fcorallini.conecta.profile.data.repository

import com.fcorallini.conecta.core.data.util.resultOf
import com.fcorallini.conecta.core.domain.model.Career
import com.fcorallini.conecta.core.domain.model.Curriculum
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.creation.data.mapper.toDomain
import com.fcorallini.conecta.creation.data.model.dto.SubjectDto
import com.fcorallini.conecta.profile.data.mapper.toDomain
import com.fcorallini.conecta.profile.data.model.dto.CurriculumIdsDto
import com.fcorallini.conecta.profile.data.model.dto.request.ProfileRequest
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

    override suspend fun setNewStudentProfile(studentId: Long, curriculums : Set<Long>, subjects : Set<Long>): Result<Unit> {
        return resultOf {
            api.setStudentProfile(
                studentId = studentId.toString(),
                profile = ProfileRequest(
                    curriculums = curriculums.map {
                        CurriculumIdsDto(
                            curriculumId = it,
                            subjectId = subjects.toList()
                        )
                    }
                )
            )
        }
    }

    override suspend fun getFollowedSubjectsIds(studentId: Long): Result<List<Long>> {
        return resultOf {
            api.getStudentProfile(studentId.toString()).curriculums.flatMap {
                it.subjects.map { it.id }
            }
        }
    }
}