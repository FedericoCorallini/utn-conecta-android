package com.fcorallini.conecta.home.data.model.dto
import com.squareup.moshi.Json

data class MeetingResponse(
    val id: Long,

    @Json(name = "subject_name")
    val subjectName: String?,

    @Json(name = "title")
    val title: String?,

    @Json(name = "date")
    val date: List<Int>,

    @Json(name = "start_time")
    val startTime: List<Int>,

    @Json(name = "end_time")
    val endTime: List<Int>,

    @Json(name = "students_number")
    val studentsNumber: Int?,

    @Json(name = "max_students")
    val maxStudents: Int?,

    @Json(name = "study_place_is_virtual")
    val studyPlaceIsVirtual: Boolean?,

    @Json(name = "study_place_location_name")
    val studyPlaceLocationName: String?,

    @Json(name = "study_place_details")
    val studyPlaceDetails: String?
)
