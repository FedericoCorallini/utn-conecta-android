package com.fcorallini.conecta.creation.domain.usecases

import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.creation.domain.repository.CreationRepository
import com.fcorallini.conecta.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val repository: CreationRepository
) {
    suspend operator fun invoke() : List<StudyPlace> {
        return repository.getLocations().getOrDefault(emptyList())
    }
}