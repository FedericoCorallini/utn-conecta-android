package com.fcorallini.conecta.creation.data.remote

import com.fcorallini.conecta.creation.data.model.dto.request.MeetingCreationRequest
import com.fcorallini.conecta.creation.data.model.dto.response.LocationResponse
import com.fcorallini.conecta.creation.data.model.dto.response.ProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CreationApi {

    @GET("api/locations")
    suspend fun getLocations(
    ) : List<LocationResponse>

    @POST("api/meetings/confirm")
    suspend fun createMeeting(
        @Body meeting: MeetingCreationRequest
    )

    @GET("api/students/{id}/profile")
    suspend fun getStudentProfile(
        @Path("id") studentId : String
    ) : ProfileResponse
}