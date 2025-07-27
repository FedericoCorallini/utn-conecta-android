package com.fcorallini.conecta.home.presentation.home

interface HomeEvent {
    data class JoinOrLeaveEvent(val id : Long) : HomeEvent
}