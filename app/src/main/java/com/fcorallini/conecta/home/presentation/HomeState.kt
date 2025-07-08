package com.fcorallini.conecta.home.presentation

import com.fcorallini.conecta.core.domain.model.Meeting

data class HomeState(
    val meetingList : List<Meeting> = emptyList(),
    val joinedMeetingIds : List<Long> = emptyList()
)
