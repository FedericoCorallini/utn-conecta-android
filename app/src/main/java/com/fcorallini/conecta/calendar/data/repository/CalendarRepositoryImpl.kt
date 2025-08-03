package com.fcorallini.conecta.calendar.data.repository

import com.fcorallini.conecta.calendar.data.remote.CalendarApi
import com.fcorallini.conecta.calendar.data.remote.dto.toDomain
import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.repository.CoreRepository
import java.time.LocalDate
import javax.inject.Inject
import com.fcorallini.conecta.calendar.domain.repository.CalendarRepository


class CalendarRepositoryImpl @Inject constructor(
    private val api: CalendarApi,
    private val coreRepository: CoreRepository
): CalendarRepository {

    override suspend fun getMeetingsForDate(date: LocalDate): List<Meeting> {
        val userId = coreRepository.getUserId()
        val formattedDate = date.toString()
        return api.getDayCalendar(userId, formattedDate).meetings.map { it.toDomain() }
    }

    override suspend fun getMeetingDays(): List<LocalDate> {
        val userId = coreRepository.getUserId()
        return api.getMeetingDays(userId).map { LocalDate.parse(it) }
    }
}
