package com.fcorallini.conecta.profile.data.remote

import com.fcorallini.conecta.profile.data.model.dto.CareerDto
import com.fcorallini.conecta.profile.data.model.dto.request.ProfileRequest
import com.fcorallini.conecta.profile.data.model.dto.response.ProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileApi {

    @GET("api/careers")
    suspend fun getCareerList() : List<CareerDto>

    @GET("api/students/{id}/profile")
    suspend fun getStudentProfile(
        @Path("id") studentId : String
    ) : ProfileResponse

    @PUT("api/students/{id}")
    suspend fun setStudentProfile(
        @Path("id") studentId : String,
        @Body profile : ProfileRequest
    ) : ProfileResponse

}