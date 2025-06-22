package com.fcorallini.conecta.authentication.data.remote

import com.fcorallini.conecta.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val authRepository: AuthRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        val token = runBlocking { authRepository.getToken() }
        val headerBuilder = request.headers.newBuilder().set(
            name = "Authorization",
            value = "Bearer $token"
        ).build()
        val modifiedRequest = request.newBuilder().headers(headerBuilder).build()
        return chain.proceed(modifiedRequest)
    }
}