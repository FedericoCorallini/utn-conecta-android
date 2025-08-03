package com.fcorallini.conecta.calendar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.calendar.domain.usecases.GetMeetingDaysUseCase
import com.fcorallini.conecta.calendar.domain.usecases.GetMeetingsForDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getMeetingsForDateUseCase: GetMeetingsForDateUseCase,
) : ViewModel() {

    private val _meetings = MutableStateFlow<List<Meeting>>(emptyList())
    val meetings: StateFlow<List<Meeting>> = _meetings

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadMeetings(date: LocalDate = LocalDate.now()) {
        viewModelScope.launch {
            try {
                _meetings.value = getMeetingsForDateUseCase(date)
                _errorMessage.value = null
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Ocurrió un error inesperado"
            }
        }
    }
}
