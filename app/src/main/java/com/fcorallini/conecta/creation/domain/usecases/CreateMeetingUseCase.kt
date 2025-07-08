package com.fcorallini.conecta.creation.domain.usecases

import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.usecases.GetUserIdUseCase
import com.fcorallini.conecta.creation.domain.repository.CreationRepository
import com.fcorallini.conecta.home.domain.repository.HomeRepository
import javax.inject.Inject

class CreateMeetingUseCase @Inject constructor(
    private val repository: CreationRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(meeting : Meeting) : String {
        val userId = getUserIdUseCase()
        val response = repository.create(meeting, userId)
        return if(response.isSuccess){
            "Reunion creada con exito"
        }
        else {
            "Error al crear reunion"
        }
    }
}