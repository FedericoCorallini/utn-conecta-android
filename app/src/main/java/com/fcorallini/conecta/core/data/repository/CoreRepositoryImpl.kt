package com.fcorallini.conecta.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.fcorallini.conecta.core.data.remote.CoreApi
import com.fcorallini.conecta.core.data.util.resultOf
import com.fcorallini.conecta.core.domain.repository.CoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoreRepositoryImpl @Inject constructor(
    private val coreDataStore: DataStore<Preferences>,
    private val coreApi: CoreApi
) : CoreRepository {

    companion object {
        private val USER_ID = longPreferencesKey("user_id")
        private val USER_EMAIL = stringPreferencesKey("user_email")
    }

    override suspend fun getUserId(): Long {
        var userId : Long? = coreDataStore.data
            .map { preferences -> preferences[USER_ID] }
            .first()

        if (userId == null) {
            resultOf {
                coreApi.getStudentId()
            }.onSuccess {
                userId = it
                putUserId(it)
            }
        }

        return userId ?: 0
    }

    override suspend fun putUserId(id: Long) {
        coreDataStore.edit { preferences ->
            preferences[USER_ID] = id
        }
    }

    override suspend fun getUserEmail(): String {
        return coreDataStore.data
            .map { preferences -> preferences[USER_EMAIL] }
            .first() ?: ""
    }

    override suspend fun putUserEmail(email: String) {
        coreDataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
        }
    }

    override suspend fun saveUserId() {
        resultOf {
            coreApi.getStudentId()
        }.onSuccess {
            putUserId(it)
        }
    }
}