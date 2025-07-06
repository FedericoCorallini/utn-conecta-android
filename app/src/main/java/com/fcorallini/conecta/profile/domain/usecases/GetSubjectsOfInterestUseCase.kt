package com.fcorallini.conecta.profile.domain.usecases

import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.core.domain.usecases.GetUserIdUseCase
import com.fcorallini.conecta.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetSubjectsOfInterestUseCase @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val repository: ProfileRepository,
    private val getFollowedCurriculumsUseCase: GetFollowedCurriculumsUseCase
){
    suspend operator fun invoke() : List<Subject> {
        val followedCurriculumsIds = getFollowedCurriculumsUseCase()
        return repository.getCareers().getOrDefault(
            emptyList()
        ).flatMap {
            it.curriculums.filter { followedCurriculumsIds.contains(it.id) }.flatMap {
                it.subjects
            }.distinct()
        }
    }
}