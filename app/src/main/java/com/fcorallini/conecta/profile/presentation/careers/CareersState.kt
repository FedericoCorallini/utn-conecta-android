package com.fcorallini.conecta.profile.presentation.careers

import com.fcorallini.conecta.core.domain.model.Curriculum

data class CareersState(
    val selectedCurriculums : List<Long> = emptyList(),
    val curriculumList : List<Curriculum> = emptyList()
)
