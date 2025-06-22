package com.fcorallini.conecta.authentication.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.fcorallini.conecta.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataStore: DataStore<Preferences>
) : AuthRepository {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

    override suspend fun getToken(): String? {
        return authDataStore.data
            .map { preferences -> preferences[TOKEN_KEY] }
            .first()
    }

    override suspend fun putToken(token: String) {
        authDataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }
}