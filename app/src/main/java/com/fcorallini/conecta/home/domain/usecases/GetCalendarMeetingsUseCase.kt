package com.fcorallini.conecta.home.domain.usecases

import com.fcorallini.conecta.core.domain.usecases.GetUserIdUseCase
import com.fcorallini.conecta.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetCalendarMeetingsUseCase @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val homeRepository: HomeRepository
){
    suspend operator fun invoke() : List<Long> {
        val studentId = getUserIdUseCase()
        val meetings = homeRepository.getCalendarMeetings(studentId).getOrDefault(emptyList())
        return meetings.map { it.id }
    }
}