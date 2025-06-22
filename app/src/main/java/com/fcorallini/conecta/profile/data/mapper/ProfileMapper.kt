package com.fcorallini.conecta.profile.data.mapper

import com.fcorallini.conecta.core.domain.model.Career
import com.fcorallini.conecta.core.domain.model.Curriculum
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.profile.data.model.dto.CareerDto
import com.fcorallini.conecta.profile.data.model.dto.ProfileCurriculumDto

fun CareerDto.toDomain() : Career {
    return Career(
        id = id,
        name = name,
        curriculums = curriculums.map {
            Curriculum(
                id = it.id ?: 0,
                name = it.name,
                subjects = it.subjects.map {
                    Subject(
                        id = it.id,
                        name = it.name
                    )
                }
            )
        }
    )
}

fun ProfileCurriculumDto.toDomain() : Curriculum {
    return Curriculum(
        id = curriculumId,
        name = curriculumName,
        subjects = emptyList()
    )
}