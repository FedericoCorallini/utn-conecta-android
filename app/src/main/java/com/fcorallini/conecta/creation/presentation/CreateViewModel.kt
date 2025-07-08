package com.fcorallini.conecta.creation.presentation

import android.os.Build
import android.util.Patterns
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.creation.domain.usecases.AttributesValidationUseCase
import com.fcorallini.conecta.creation.domain.usecases.CreateMeetingUseCase
import com.fcorallini.conecta.creation.domain.usecases.GetFollowedSubjectsUseCase
import com.fcorallini.conecta.creation.domain.usecases.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CreateViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val createMeetingUseCase: CreateMeetingUseCase,
    private val getFollowedSubjectsUseCase: GetFollowedSubjectsUseCase,
    private val attributesValidationUseCase: AttributesValidationUseCase
) : ViewModel() {

    var state by mutableStateOf(CreateState())
        private set

    init {
        viewModelScope.launch {
            val locations = getLocationsUseCase()
            val followedSubjects = getFollowedSubjectsUseCase()
            if(locations.isNotEmpty() && followedSubjects.isNotEmpty()){
                state = state.copy(
                    subjectList = followedSubjects,
                    subjectName = followedSubjects[0].name,
                    idSubject = followedSubjects[0].id,
                    locations = locations,
                    studyPlaceLocationName = locations[0].location
                )
            }
        }
    }

    fun onEvent(event: CreateEvent) {
        when (event) {
            is CreateEvent.DateChanged -> onDateChanged(event.date)
            is CreateEvent.StartTimeChanged -> onStartTimeChanged(event.startTime)
            is CreateEvent.EndTimeChanged -> onEndTimeChanged(event.endTime)
            is CreateEvent.MaxStudentsChanged -> onMaxStudentsChanged(event.maxStudents)
            is CreateEvent.TitleChanged -> onTitleChanged(event.value)
            is CreateEvent.StudyPlaceDetailsChanged -> onStudyPlaceDetailsChanged(event.value)
            is CreateEvent.StudyPlaceLocationNameChanged -> onStudyPlaceLocationNameChanged(event.location)
            is CreateEvent.SubjectNameChanged -> onSubjectNameChanged(event.name)
            CreateEvent.Submit -> submitForm()
        }
    }

    private fun onDateChanged(date: LocalDate) {
        state = state.copy(date = date)
    }

    private fun onStartTimeChanged(startTime: LocalTime) {
        state = state.copy(startTime = startTime)
    }

    private fun onEndTimeChanged(endTime: LocalTime) {
        state = state.copy(endTime = endTime)
    }

    private fun onMaxStudentsChanged(maxStudents: Int) {
        state = state.copy(maxStudents = maxStudents)
    }

    private fun onTitleChanged(title: String) {
        state = state.copy(title = title)
    }

    private fun onStudyPlaceDetailsChanged(details: String) {
        state = state.copy(studyPlaceDetails = details)
    }

    private fun onStudyPlaceLocationNameChanged(location: String) {
        state = state.copy(studyPlaceLocationName = location)
    }

    private fun onSubjectNameChanged(name : String) {
        val subjectId : Long = state.subjectList.find {
            it.name == name
        }?.id ?: 0
        state = state.copy(subjectName = name, idSubject = subjectId)
    }

    private fun submitForm() {
        val studyPlace : StudyPlace = state.locations.find {
            state.studyPlaceLocationName == it.location
        } ?: state.locations[0]

        state = state.copy(
            toastMessage = null,
            dateErrorMessage = attributesValidationUseCase.validateDate(state.date),
            timeErrorMessage = attributesValidationUseCase.validateTime(state.startTime, state.endTime),
            titleErrorMessage = attributesValidationUseCase.validateTitle(state.title),
            maxStudentsErrorMessage = attributesValidationUseCase.validateStudentNumber(state.maxStudents),
            studyPlaceDetailsErrorMessage = attributesValidationUseCase.validateStudyPlaceDetails(studyPlace, state.studyPlaceDetails)
        )

        if(!isErrorMessage()){
            viewModelScope.launch {
                val message = createMeetingUseCase(
                    meeting = Meeting(
                        id = 0,
                        date = state.date,
                        startTime = state.startTime,
                        endTime = state.endTime,
                        maxStudents = state.maxStudents,
                        title = state.title,
                        studyPlace = StudyPlace(
                            id = studyPlace.id,
                            location = studyPlace.location,
                            isVirtual = studyPlace.isVirtual,
                            details = state.studyPlaceDetails
                        ),
                        subject = Subject(
                            id = state.idSubject,
                            name = state.subjectName
                        ),
                    )
                )
                state = state.copy(toastMessage = message)
            }
        }
    }

    private fun isErrorMessage() : Boolean {
        return  state.dateErrorMessage != null ||
                state.timeErrorMessage != null ||
                state.titleErrorMessage != null ||
                state.maxStudentsErrorMessage != null ||
                state.studyPlaceDetailsErrorMessage != null
    }

}