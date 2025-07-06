package com.fcorallini.conecta.profile.presentation.careers

sealed interface CareerEvent {
    data class FollowCurriculum(val id : Long) : CareerEvent
}