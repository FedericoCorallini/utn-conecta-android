package com.fcorallini.conecta.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.fcorallini.conecta.authentication.domain.repository.AuthRepository
import com.fcorallini.conecta.authentication.data.remote.TokenInterceptor
import com.fcorallini.conecta.core.data.remote.CoreApi
import com.fcorallini.conecta.core.data.repository.CoreRepositoryImpl
import com.fcorallini.conecta.core.domain.repository.CoreRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoreModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(authRepository: AuthRepository) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(authRepository))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("preferences")
        }
    }

    @Provides
    @Singleton
    fun provideCoreApi(client : OkHttpClient) : CoreApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(CoreApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoreRepository(
        dataStore: DataStore<Preferences>,
        coreApi: CoreApi
    ) : CoreRepository {
        return CoreRepositoryImpl(dataStore, coreApi)
    }
}