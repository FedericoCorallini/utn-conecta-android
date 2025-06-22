package com.fcorallini.conecta.profile.presentation.careers

sealed interface CareerEvent {
    data class FollowCareer(val id : Long) : CareerEvent
}