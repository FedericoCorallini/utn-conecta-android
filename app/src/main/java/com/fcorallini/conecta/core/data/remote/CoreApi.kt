package com.fcorallini.conecta.core.data.remote

import com.fcorallini.conecta.core.domain.model.Meeting
import retrofit2.http.GET
import retrofit2.http.Path

interface CoreApi {

    @GET("api/students/id")
    suspend fun getStudentId() : Long

    @GET("api/meetings/students/{userId}")
    suspend fun getMeetingsForUser(@Path("userId") userId: Int): List<Meeting>
}
