package com.fcorallini.conecta.home.di

import com.fcorallini.conecta.home.data.remote.HomeApi
import com.fcorallini.conecta.home.data.repository.HomeRepositoryImpl
import com.fcorallini.conecta.home.domain.repository.HomeRepository
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
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeApi(client : OkHttpClient) : HomeApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("http://192.168.1.26:8080/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(homeApi: HomeApi) : HomeRepository {
        return HomeRepositoryImpl(homeApi)
    }
}