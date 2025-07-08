package com.fcorallini.conecta.home.domain.usecases

import com.fcorallini.conecta.core.domain.usecases.GetUserEmailUseCase
import com.fcorallini.conecta.home.domain.repository.HomeRepository
import javax.inject.Inject

class JoinMeetingUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val getUserEmailUseCase: GetUserEmailUseCase
) {
    suspend operator fun invoke(meetingId : Long) : Boolean {
        val email = getUserEmailUseCase()
        val result = homeRepository.joinMeeting(email, meetingId)
        return result.isSuccess
    }
}