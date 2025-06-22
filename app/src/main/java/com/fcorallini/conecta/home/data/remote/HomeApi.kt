package com.fcorallini.conecta.home.data.remote

import com.fcorallini.conecta.home.data.model.dto.MeetingResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {

    @GET("api/meetings/students/{id}")
    suspend fun getMeetingsForStudent(
        @Path("id") studentId : String
    ) : List<MeetingResponse>

}