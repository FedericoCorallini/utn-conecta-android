package com.fcorallini.conecta.profile.domain.usecases

import com.fcorallini.conecta.core.domain.model.Curriculum
import com.fcorallini.conecta.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetCurriculumsUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke() : List<Curriculum> {
        return profileRepository.getCareers().getOrDefault(
                emptyList()
            ).flatMap {
                val careerName = it.name
                it.curriculums.map {
                    Curriculum(
                        id = it.id, // TODO backend needs to provide curriculum ids
                        name = careerName + ":\n" + it.name,
                        subjects = emptyList()
                    )
                }
            }
        }
    }