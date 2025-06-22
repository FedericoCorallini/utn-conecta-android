package com.fcorallini.conecta.profile.presentation.subjects

sealed interface SubjectsEvent {
    data class Follow(val id : Long) : SubjectsEvent
}