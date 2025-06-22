package com.fcorallini.conecta.core.domain.usecases

import com.fcorallini.conecta.core.domain.repository.CoreRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val coreRepository: CoreRepository
) {
    suspend operator fun invoke() : Long {
        return 3
        // return coreRepository.getUserId()
    }
}