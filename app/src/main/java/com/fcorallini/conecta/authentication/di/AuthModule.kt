package com.fcorallini.conecta.authentication.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.fcorallini.conecta.authentication.data.repository.AuthRepositoryImpl
import com.fcorallini.conecta.authentication.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(dataStore: DataStore<Preferences>) : AuthRepository {
        return AuthRepositoryImpl(dataStore)
    }
}