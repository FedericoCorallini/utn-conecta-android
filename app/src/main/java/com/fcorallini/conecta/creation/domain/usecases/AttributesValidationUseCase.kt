package com.fcorallini.conecta.creation.domain.usecases

import com.fcorallini.conecta.core.domain.model.StudyPlace
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

class AttributesValidationUseCase @Inject constructor() {

    fun validateStudyPlaceDetails(studyPlace: StudyPlace, details: String) : String? {
        val urlRegex = Regex("""https?://([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?""")
        return when {
            details.isEmpty() -> "Debe ingresar un detalle para el lugar"
            studyPlace.isVirtual && !urlRegex.matches(details) -> "El detalle debe ser una url"
            else -> null
        }
    }

    fun validateTitle(title: String) : String? {
        return if(title.isEmpty()) "Debe ingresar un titulo" else null
    }

    fun validateDate(date: LocalDate) : String? {
        return if(date.isBefore(LocalDate.now())) "La fecha no debe ser anterior a la del dia actual" else null
    }

    fun validateTime(startTime: LocalTime, endTime: LocalTime) : String? {
        return if(endTime.isBefore(startTime)) "La hora de inicio debe ser previa a la hora de finalizacion" else null
    }

    fun validateStudentNumber(number: Int) : String? {
        return if(number < 2) "El minimo de estudiantes permitidos es 2" else null
    }
}