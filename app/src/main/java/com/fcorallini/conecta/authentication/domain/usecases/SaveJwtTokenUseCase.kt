package com.fcorallini.conecta.authentication.domain.usecases

import com.fcorallini.conecta.authentication.domain.repository.AuthRepository
import javax.inject.Inject

class SaveJwtTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(token : String) {
        authRepository.putToken(token)
    }
}