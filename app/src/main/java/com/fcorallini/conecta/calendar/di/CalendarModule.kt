package com.fcorallini.conecta.calendar.di

import com.fcorallini.conecta.calendar.data.remote.CalendarApi
import com.fcorallini.conecta.calendar.data.repository.CalendarRepositoryImpl
import com.fcorallini.conecta.calendar.domain.repository.CalendarRepository
import com.fcorallini.conecta.calendar.domain.usecases.GetMeetingDaysUseCase
import com.fcorallini.conecta.calendar.domain.usecases.GetMeetingsForDateUseCase
import com.fcorallini.conecta.core.domain.repository.CoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Module
@InstallIn(SingletonComponent::class)
object CalendarModule {

    @Provides
    @Singleton
    fun provideCalendarApi(client: OkHttpClient): CalendarApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(CalendarApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCalendarRepository(
        api: CalendarApi,
        coreRepository: CoreRepository
    ): CalendarRepository {
        return CalendarRepositoryImpl(api, coreRepository)
    }

    @Provides
    @Singleton
    fun provideGetMeetingsForDateUseCase(
        repository: CalendarRepository
    ): GetMeetingsForDateUseCase = GetMeetingsForDateUseCase(repository)

    @Provides
    @Singleton
    fun provideGetMeetingDaysUseCase(
        repository: CalendarRepository
    ): GetMeetingDaysUseCase = GetMeetingDaysUseCase(repository)

}
