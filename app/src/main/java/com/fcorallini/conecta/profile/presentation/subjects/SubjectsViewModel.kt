package com.fcorallini.conecta.profile.presentation.subjects

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcorallini.conecta.profile.domain.usecases.GetFollowedSubjectsIdsUseCase
import com.fcorallini.conecta.profile.domain.usecases.GetSubjectsOfInterestUseCase
import com.fcorallini.conecta.profile.domain.usecases.SetStudentProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectsViewModel @Inject constructor(
    private val getSubjectsOfInterestUseCase : GetSubjectsOfInterestUseCase,
    private val getFollowedSubjectsIdsUseCase: GetFollowedSubjectsIdsUseCase,
    private val setStudentProfileUseCase: SetStudentProfileUseCase
) : ViewModel(){

    var state by mutableStateOf(SubjectsState())
        private set

    init {
        viewModelScope.launch {
            val subjects = getSubjectsOfInterestUseCase()
            val studentSubjects = getFollowedSubjectsIdsUseCase()
            val (followed, notFollowed) = subjects.partition { studentSubjects.contains(it.id) }
            state = state.copy(
                subjects = followed + notFollowed,
                followedSubjectsIds = studentSubjects
            )
        }
    }

    fun onEvent(event: SubjectsEvent) {
        when(event) {
            is SubjectsEvent.Follow -> {
                val newFollowed = if(state.followedSubjectsIds.contains(event.id)) {
                    state.followedSubjectsIds - event.id
                }else{
                    state.followedSubjectsIds + event.id
                }
                viewModelScope.launch {
                    val success = setStudentProfileUseCase(
                        curriculumsSet = null,
                        subjectsSet = newFollowed.toSet()
                    )
                    if (success) {
                        state = state.copy(
                            followedSubjectsIds = newFollowed
                        )
                    }
                }
            }
        }
    }
}