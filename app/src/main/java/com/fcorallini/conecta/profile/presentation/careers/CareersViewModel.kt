package com.fcorallini.conecta.profile.presentation.careers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcorallini.conecta.profile.domain.usecases.GetCurriculumsUseCase
import com.fcorallini.conecta.profile.domain.usecases.GetFollowedCurriculumsUseCase
import com.fcorallini.conecta.profile.domain.usecases.SetStudentProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CareersViewModel @Inject constructor(
    private val getCurriculumsUseCase: GetCurriculumsUseCase,
    private val getFollowedCurriculumsUseCase: GetFollowedCurriculumsUseCase,
    private val setStudentProfileUseCase: SetStudentProfileUseCase
) : ViewModel() {

    var state by mutableStateOf(CareersState())
        private set

    init {
        viewModelScope.launch {
            val curriculums = getCurriculumsUseCase()
            val studentCurriculums = getFollowedCurriculumsUseCase().toList()
            state = state.copy(
                curriculumList = curriculums,
                selectedCurriculums = studentCurriculums
            )
        }
    }

    fun onEvent(event: CareerEvent) {
        when(event) {
            is CareerEvent.FollowCurriculum -> {
                val newSelectedCurriculums = if(state.selectedCurriculums.contains(event.id)) {
                    state.selectedCurriculums - event.id
                }else{
                    state.selectedCurriculums + event.id
                }
                viewModelScope.launch {
                    val success = setStudentProfileUseCase(
                        curriculumsSet = newSelectedCurriculums.toSet(),
                        subjectsSet = null
                    )
                    if (success) {
                        state = state.copy(
                            selectedCurriculums = newSelectedCurriculums // TODO call api to set new profile
                        )
                    }
                }
            }
        }
    }
}