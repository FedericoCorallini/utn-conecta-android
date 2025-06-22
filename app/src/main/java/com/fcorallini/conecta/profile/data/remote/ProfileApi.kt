package com.fcorallini.conecta.profile.data.remote

import com.fcorallini.conecta.profile.data.model.dto.CareerDto
import com.fcorallini.conecta.profile.data.model.dto.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApi {

    @GET("api/careers")
    suspend fun getCareerList() : List<CareerDto>

    @GET("api/students/{id}/profile")
    suspend fun getStudentProfile(
        @Path("id") studentId : String
    ) : ProfileResponse

}