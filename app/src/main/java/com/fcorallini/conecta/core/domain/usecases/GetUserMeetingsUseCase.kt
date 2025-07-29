package com.fcorallini.conecta.core.domain.usecases

import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.repository.CoreRepository

class GetUserMeetingsUseCase(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(userId: Int): List<Meeting> {
        return repository.getUserMeetings(userId)
    }
}
