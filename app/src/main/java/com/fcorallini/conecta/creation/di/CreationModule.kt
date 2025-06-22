package com.fcorallini.conecta.creation.di

import com.fcorallini.conecta.creation.data.remote.CreationApi
import com.fcorallini.conecta.creation.data.repository.CreationRepositoryImpl
import com.fcorallini.conecta.creation.domain.repository.CreationRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CreationModule {

    @Provides
    @Singleton
    fun provideCreationApi(client : OkHttpClient) : CreationApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("http://192.168.1.26:8080/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(CreationApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCreationRepository(creationApi: CreationApi) : CreationRepository {
        return CreationRepositoryImpl(creationApi)
    }
}