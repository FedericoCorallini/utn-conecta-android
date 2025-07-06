package com.fcorallini.conecta.profile.domain.usecases

import com.fcorallini.conecta.core.domain.usecases.GetUserIdUseCase
import com.fcorallini.conecta.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class SetStudentProfileUseCase @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getSubjectsOfInterestUseCase: GetSubjectsOfInterestUseCase,
    private val getFollowedCurriculumsUseCase: GetFollowedCurriculumsUseCase,
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(subjectsSet : Set<Long>?, curriculumsSet: Set<Long>?) : Boolean {
        val studentId = getUserIdUseCase()
        val newCurriculums : Set<Long> = curriculumsSet ?: getFollowedCurriculumsUseCase()
        val newFollowedSubjects : Set<Long> = subjectsSet ?: getSubjectsOfInterestUseCase().map { it.id }.toSet()
        return profileRepository.setNewStudentProfile(studentId, newCurriculums, newFollowedSubjects).isSuccess
    }
}