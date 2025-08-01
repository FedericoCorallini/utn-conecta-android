package com.fcorallini.conecta.core.data.remote.dto

data class MeetingDto(
    val id: Long,
    val date: List<Int>,
    val start_time: List<Int>,
    val end_time: List<Int>,
    val students_number: Int,
    val max_students: Int,
    val title: String,
    val study_place_is_virtual: Boolean,
    val study_place_location_name: String,
    val study_place_details: String,
    val subject_name: String
)
