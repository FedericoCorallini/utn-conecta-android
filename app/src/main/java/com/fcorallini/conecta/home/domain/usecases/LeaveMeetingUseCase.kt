package com.fcorallini.conecta.home.domain.usecases

import com.fcorallini.conecta.core.domain.usecases.GetUserIdUseCase
import com.fcorallini.conecta.home.domain.repository.HomeRepository
import javax.inject.Inject

class LeaveMeetingUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(meetingId : Long) : Boolean {
        val userId = getUserIdUseCase()
        val result = homeRepository.unlinkMeeting(userId, meetingId)
        return result.isSuccess
    }
}