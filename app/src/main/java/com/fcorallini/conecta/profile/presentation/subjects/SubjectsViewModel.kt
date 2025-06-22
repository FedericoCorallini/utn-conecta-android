package com.fcorallini.conecta.profile.presentation.subjects

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcorallini.conecta.profile.domain.usecases.GetSubjectsOfInterestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectsViewModel @Inject constructor(
    private val getSubjectsOfInterestUseCase : GetSubjectsOfInterestUseCase
) : ViewModel(){

    var state by mutableStateOf(SubjectsState())
        private set

    init {
        viewModelScope.launch {
            val subjects = getSubjectsOfInterestUseCase()
            state = state.copy(subjects = subjects)
        }
    }

    fun onEvent(event: SubjectsEvent) {
        when(event) {
            is SubjectsEvent.Follow -> {
                val newFollowed = if(state.followedSubjectsIds.contains(event.id)) {
                    state.followedSubjectsIds - event.id
                }else{
                    state.followedSubjectsIds + event.id
                }
                state = state.copy(
                    followedSubjectsIds = newFollowed // TODO call api to set new profile
                )
            }
        }
    }
}