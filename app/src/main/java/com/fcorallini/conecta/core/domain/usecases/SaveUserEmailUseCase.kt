package com.fcorallini.conecta.core.domain.usecases

import com.fcorallini.conecta.core.domain.repository.CoreRepository
import javax.inject.Inject

class SaveUserEmailUseCase @Inject constructor(
    private val coreRepository: CoreRepository
) {
    suspend operator fun invoke(email : String) {
        coreRepository.putUserEmail(email)
    }
}