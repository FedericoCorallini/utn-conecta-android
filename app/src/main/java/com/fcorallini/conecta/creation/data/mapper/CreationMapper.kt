package com.fcorallini.conecta.creation.data.mapper

import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.creation.data.model.dto.SubjectDto
import com.fcorallini.conecta.creation.data.model.dto.response.LocationResponse

fun LocationResponse.toDomain() : StudyPlace {
    return StudyPlace(
        id = id,
        location = name,
        isVirtual = virtual
    )
}

fun SubjectDto.toDomain() : Subject {
    return Subject(
        id = id,
        name = name
    )
}