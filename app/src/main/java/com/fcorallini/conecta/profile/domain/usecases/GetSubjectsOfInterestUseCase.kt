package com.fcorallini.conecta.profile.domain.usecases

import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.core.domain.usecases.GetUserIdUseCase
import com.fcorallini.conecta.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetSubjectsOfInterestUseCase @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val repository: ProfileRepository
){
    suspend operator fun invoke() : List<Subject> {
        val userId = getUserIdUseCase()
        val followedCurriculumsIds = repository.getFollowedCurriculums(userId).getOrDefault(emptyList()).map { it.id } + 0 // TODO eliminate 0 when curriculums have their ids
        return repository.getCareers().getOrDefault(
            emptyList()
        ).flatMap {
            it.curriculums.filter { followedCurriculumsIds.contains(it.id) }.flatMap {
                it.subjects
            }.distinct()
        }
    }
}