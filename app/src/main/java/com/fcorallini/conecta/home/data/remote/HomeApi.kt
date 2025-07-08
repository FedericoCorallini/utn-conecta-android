package com.fcorallini.conecta.home.data.remote

import com.fcorallini.conecta.home.data.model.dto.CalendarResponse
import com.fcorallini.conecta.home.data.model.dto.JoinRequest
import com.fcorallini.conecta.home.data.model.dto.MeetingResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {

    @GET("api/meetings/students/{id}")
    suspend fun getMeetingsForStudent(
        @Path("id") studentId : Long
    ) : List<MeetingResponse>

    @GET("api/calendars/{id}")
    suspend fun getCalendarMeetings(
        @Path("id") studentId : String
    ) : CalendarResponse

    @POST("api/meetings/register")
    suspend fun joinMeeting(
        @Body joinRequest : JoinRequest
    )

    @DELETE("api/meetings/unlink")
    suspend fun unlinkMeeting(
        @Query("studentId") studentId : Long,
        @Query("meetingId") meetingId : Long
    )
}