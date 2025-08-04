package com.fcorallini.conecta.calendar.domain.repository

import com.fcorallini.conecta.core.domain.model.Meeting
import java.time.LocalDate

interface CalendarRepository {
    suspend fun getMeetingsForDate(date: LocalDate): List<Meeting>
    suspend fun getMeetingDays(): List<LocalDate>
}
