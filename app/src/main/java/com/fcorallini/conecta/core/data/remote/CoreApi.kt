package com.fcorallini.conecta.core.data.remote

import retrofit2.http.GET

interface CoreApi {

    @GET("api/students/id")
    suspend fun getStudentId() : Long

}
