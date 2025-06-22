package com.fcorallini.conecta.creation.data.repository

import com.fcorallini.conecta.creation.data.model.dto.StudyPlaceDto
import com.fcorallini.conecta.creation.data.model.dto.SubjectDto
import com.fcorallini.conecta.creation.data.model.dto.request.MeetingCreationRequest
import com.fcorallini.conecta.creation.data.remote.CreationApi
import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.creation.data.mapper.toDomain
import com.fcorallini.conecta.creation.domain.repository.CreationRepository
import retrofit2.HttpException
import javax.inject.Inject

class CreationRepositoryImpl @Inject constructor(
    private val api: CreationApi
) : CreationRepository {

    override suspend fun getLocations(): Result<List<StudyPlace>> {
        return try {
            val apiResponse = api.getLocations()
            Result.success(apiResponse.map { it.toDomain() })
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> {
                    Result.failure(e)
                }
                403 -> {
                    Result.failure(e)
                }
                else -> {
                    Result.failure(e)
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun create(meeting: Meeting) : Result<Unit> {
        return try {
            api.createMeeting(
                meeting = MeetingCreationRequest(
                    date = meeting.date.toString(),
                    startTime = meeting.startTime.toString(),
                    endTime = meeting.endTime.toString(),
                    maxStudents = meeting.maxStudents,
                    title = meeting.title,
                    studyPlace = StudyPlaceDto(
                        locationId = meeting.studyPlace.id ?: 0,
                        details = meeting.studyPlace.details
                    ),
                    idCreator = 1,
                    idSubject = meeting.subject.id
                )
            )
            Result.success(Unit)
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> {
                    Result.failure(e)
                }
                403 -> {
                    Result.failure(e)
                }
                else -> {
                    Result.failure(e)
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFollowedSubjects(studentId: Long): Result<List<Subject>> {
        return try {
            val apiResponse = api.getStudentProfile(studentId.toString())
            val subjectList : List<SubjectDto> = apiResponse.subjectsFollowed.flatMap {
                it.subjects
            }.toList()
            return Result.success(subjectList.map { it.toDomain() })
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> {
                    Result.failure(e)
                }
                403 -> {
                    Result.failure(e)
                }
                else -> {
                    Result.failure(e)
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}