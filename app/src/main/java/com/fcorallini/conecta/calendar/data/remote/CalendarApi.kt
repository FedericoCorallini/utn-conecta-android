package com.fcorallini.conecta.calendar.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import com.fcorallini.conecta.calendar.data.remote.dto.DayCalendarDto


interface CalendarApi {

    @GET("api/calendars/{id}/{date}")
    suspend fun getDayCalendar(
        @Path("id") userId: Long,
        @Path("date") date: String
    ): DayCalendarDto

    @GET("api/calendars/{id}/days")
    suspend fun getMeetingDays(@Path("id") userId: Long): List<String>
}