package com.fcorallini.conecta.home.domain.usecases

import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.usecases.GetUserIdUseCase
import com.fcorallini.conecta.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetMeetingsUseCase @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val repository : HomeRepository
) {
    suspend operator fun invoke() : List<Meeting> {
        val userId = getUserIdUseCase()
        val meetings = repository.getMeetingsForStudent(userId)
        return meetings.getOrDefault(emptyList())
    }
}