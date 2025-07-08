package com.fcorallini.conecta.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcorallini.conecta.home.domain.usecases.GetCalendarMeetingsUseCase
import com.fcorallini.conecta.home.domain.usecases.GetMeetingsUseCase
import com.fcorallini.conecta.home.domain.usecases.JoinMeetingUseCase
import com.fcorallini.conecta.home.domain.usecases.LeaveMeetingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMeetingsUseCase: GetMeetingsUseCase,
    private val getCalendarMeetingsUseCase: GetCalendarMeetingsUseCase,
    private val leaveMeetingUseCase: LeaveMeetingUseCase,
    private val joinMeetingUseCase: JoinMeetingUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getMeetingsFromApi()
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.JoinOrLeaveEvent -> {
                val meetingId = event.id
                if (state.joinedMeetingIds.contains(meetingId)){
                    leaveMeeting(meetingId)
                }
                else {
                    joinMeeting(meetingId)
                }
            }
        }
    }

    private fun getMeetingsFromApi() {
        viewModelScope.launch {
            val meetings = getMeetingsUseCase()
            val joinedMeetings = getCalendarMeetingsUseCase()
            state = state.copy(
                meetingList = meetings,
                joinedMeetingIds = joinedMeetings
            )
        }
    }

    private fun leaveMeeting(meetingId : Long) {
        viewModelScope.launch {
            val success = leaveMeetingUseCase(meetingId)
            if(success) getMeetingsFromApi()
        }
    }

    private fun joinMeeting(meetingId : Long) {
        viewModelScope.launch {
            val success = joinMeetingUseCase(meetingId)
            if(success) getMeetingsFromApi()
        }
    }

}