package com.fcorallini.conecta.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcorallini.conecta.home.domain.usecases.GetMeetingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMeetingsUseCase: GetMeetingsUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            val meetings = getMeetingsUseCase()
            state = state.copy(meetingList = meetings)
        }
    }

    fun onEvent(event: HomeEvent) {
        when(event) {

        }
    }
}