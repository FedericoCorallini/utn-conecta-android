package com.fcorallini.conecta.core.domain.repository

interface CoreRepository {
    suspend fun putUserId(id : Long)
    suspend fun getUserId() : Long
}