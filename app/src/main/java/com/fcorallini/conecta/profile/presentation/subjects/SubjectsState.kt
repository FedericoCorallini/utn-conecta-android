package com.fcorallini.conecta.profile.presentation.subjects

import com.fcorallini.conecta.core.domain.model.Subject

data class SubjectsState(
    val followedSubjectsIds : List<Long> = emptyList(),
    val subjects : List<Subject> = emptyList()
)
