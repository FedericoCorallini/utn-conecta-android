package com.fcorallini.conecta.profile.domain.usecases

import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.core.domain.usecases.GetUserIdUseCase
import com.fcorallini.conecta.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetFollowedCurriculumsUseCase @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() : Set<Long> {
        val userId = getUserIdUseCase()
        return repository.getFollowedCurriculums(userId).getOrDefault(emptyList()).map { it.id }.toSet()
    }
}