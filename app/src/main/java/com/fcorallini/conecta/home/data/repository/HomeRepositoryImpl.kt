package com.fcorallini.conecta.home.data.repository

import com.fcorallini.conecta.core.data.util.resultOf
import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.home.data.mapper.toDomain
import com.fcorallini.conecta.home.data.model.dto.JoinRequest
import com.fcorallini.conecta.home.data.remote.HomeApi
import com.fcorallini.conecta.home.domain.repository.HomeRepository
import retrofit2.HttpException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApi : HomeApi
): HomeRepository {

    override suspend fun getMeetingsForStudent(studentId: Long): Result<List<Meeting>> {
        return try {
            val apiResponse = homeApi.getMeetingsForStudent(studentId)
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

    override suspend fun getCalendarMeetings(studentId: Long): Result<List<Meeting>> {
       return resultOf {
           homeApi.getCalendarMeetings(studentId.toString()).dayCalendarList.flatMap {
               it.meetings.map {
                   it.toDomain()
               }
           }
       }
    }

    override suspend fun unlinkMeeting(studentId: Long, meetingId: Long): Result<Unit> {
        return resultOf {
            homeApi.unlinkMeeting(studentId, meetingId)
        }
    }

    override suspend fun joinMeeting(studentEmail: String, meetingId: Long): Result<Unit> {
        return resultOf {
            homeApi.joinMeeting(
                JoinRequest(meetingId, studentEmail)
            )
        }
    }
}