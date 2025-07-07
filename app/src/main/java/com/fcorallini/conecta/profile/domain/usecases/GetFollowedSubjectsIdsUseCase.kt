package com.fcorallini.conecta.profile.domain.usecases

import com.fcorallini.conecta.core.domain.usecases.GetUserIdUseCase
import com.fcorallini.conecta.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetFollowedSubjectsIdsUseCase @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() : List<Long> {
        val userId = getUserIdUseCase()
        return repository.getFollowedSubjectsIds(userId).getOrDefault(emptyList())
    }
}