package com.fcorallini.conecta.creation.data.model.dto.request

import com.fcorallini.conecta.creation.data.model.dto.StudyPlaceDto
import com.squareup.moshi.Json

data class MeetingCreationRequest (

    val date : String,

    @Json(name = "start_time")
    val startTime : String,

    @Json(name = "end_time")
    val endTime : String,

    @Json(name = "max_students")
    val maxStudents : Int,

    val title : String,

    @Json(name = "study_place")
    val studyPlace : StudyPlaceDto,

    @Json(name = "id_creator")
    val idCreator : Long,

    @Json(name = "id_subject")
    val idSubject : Long
)