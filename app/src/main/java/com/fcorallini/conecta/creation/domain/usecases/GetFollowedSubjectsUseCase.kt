package com.fcorallini.conecta.creation.domain.usecases

import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.core.domain.usecases.GetUserIdUseCase
import com.fcorallini.conecta.creation.domain.repository.CreationRepository
import com.fcorallini.conecta.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetFollowedSubjectsUseCase @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val repository: CreationRepository
) {
    suspend operator fun invoke() : List<Subject> {
        val userId = getUserIdUseCase()
        return repository.getFollowedSubjects(userId).getOrDefault(emptyList())
    }
}