package com.fcorallini.conecta.core.domain.usecases

import com.fcorallini.conecta.core.domain.repository.CoreRepository
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(
    private val repository: CoreRepository
) {
    suspend operator fun invoke() {
        repository.saveUserId()
    }
}