package com.fcorallini.conecta.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.fcorallini.conecta.core.domain.repository.CoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoreRepositoryImpl @Inject constructor(
    private val coreDataStore: DataStore<Preferences>
) : CoreRepository {

    companion object {
        private val USER_ID = longPreferencesKey("user_id")
    }

    override suspend fun getUserId(): Long {
        return coreDataStore.data
            .map { preferences -> preferences[USER_ID] }
            .first() ?: 0
    }

    override suspend fun putUserId(id: Long) {
        coreDataStore.edit { preferences ->
            preferences[USER_ID] = id
        }
    }
}