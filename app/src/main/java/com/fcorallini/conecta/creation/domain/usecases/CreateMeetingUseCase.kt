package com.fcorallini.conecta.creation.domain.usecases

import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.creation.domain.repository.CreationRepository
import com.fcorallini.conecta.home.domain.repository.HomeRepository
import javax.inject.Inject

class CreateMeetingUseCase @Inject constructor(
    private val repository: CreationRepository
) {
    suspend operator fun invoke(meeting : Meeting) : String {
        val response = repository.create(meeting)
        return if(response.isSuccess){
            "Reunion creada con exito"
        }
        else {
            "Error al crear reunion"
        }
    }
}