package com.fcorallini.conecta.home.data.model.dto

import com.squareup.moshi.Json

data class CalendarResponse (
    @Json(name = "day_calendar_list")
    val dayCalendarList: List<CalendarDayDto>
)
