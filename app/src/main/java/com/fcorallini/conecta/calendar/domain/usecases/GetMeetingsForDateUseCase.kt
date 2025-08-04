package com.fcorallini.conecta.calendar.domain.usecases

import com.fcorallini.conecta.calendar.domain.repository.CalendarRepository
import com.fcorallini.conecta.core.domain.model.Meeting
import java.time.LocalDate
import javax.inject.Inject

class GetMeetingsForDateUseCase @Inject constructor(
    private val repository: CalendarRepository
) {
    suspend operator fun invoke(date: LocalDate): List<Meeting> {
        return repository.getMeetingsForDate(date)
    }
}
