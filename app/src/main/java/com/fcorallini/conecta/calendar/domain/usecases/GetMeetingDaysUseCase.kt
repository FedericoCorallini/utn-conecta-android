package com.fcorallini.conecta.calendar.domain.usecases

import com.fcorallini.conecta.calendar.domain.repository.CalendarRepository
import java.time.LocalDate
import javax.inject.Inject

class GetMeetingDaysUseCase @Inject constructor(
    private val repository: CalendarRepository
) {
    suspend operator fun invoke(): List<LocalDate> {
        return repository.getMeetingDays()
    }
}
