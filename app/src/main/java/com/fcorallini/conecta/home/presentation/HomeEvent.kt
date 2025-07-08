package com.fcorallini.conecta.home.presentation

interface HomeEvent {
    data class JoinOrLeaveEvent(val id : Long) : HomeEvent
}