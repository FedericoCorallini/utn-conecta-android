package com.fcorallini.conecta.authentication.domain.repository

interface AuthRepository {
    suspend fun getToken() : String?
    suspend fun putToken(token : String)
}