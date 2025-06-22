package com.fcorallini.conecta.profile.presentation.careers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcorallini.conecta.profile.domain.usecases.GetCurriculumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CareersViewModel @Inject constructor(
    private val getCurriculumsUseCase: GetCurriculumsUseCase
) : ViewModel() {

    var state by mutableStateOf(CareersState())
        private set

    init {
        viewModelScope.launch {
            val curriculums = getCurriculumsUseCase()
            state = state.copy(curriculumList = curriculums)
        }
    }

    fun onEvent(event: CareerEvent) {
        when(event) {
            is CareerEvent.FollowCareer -> {
                val newSelectedCareers = if(state.selectedCareers.contains(event.id)) {
                    state.selectedCareers - event.id
                }else{
                    state.selectedCareers + event.id
                }
                state = state.copy(
                    selectedCareers = newSelectedCareers // TODO call api to set new profile
                )
            }
        }
    }
}