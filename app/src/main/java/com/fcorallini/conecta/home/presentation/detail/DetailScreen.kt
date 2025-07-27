package com.fcorallini.conecta.home.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.core.presentation.components.MeetingFullCard
import com.fcorallini.conecta.core.presentation.components.TopBar
import com.fcorallini.conecta.core.presentation.theme.ConectaTheme
import com.fcorallini.conecta.home.presentation.home.HomeEvent
import com.fcorallini.conecta.home.presentation.home.HomeViewModel
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun DetailScreen(
    selectedMeetingId : Long,
    navController : NavHostController,
    viewModel: HomeViewModel
) {
    val meeting : Meeting? = viewModel.state.meetingList.find { meeting: Meeting -> meeting.id == selectedMeetingId }
    val isJoined = viewModel.state.joinedMeetingIds.contains(selectedMeetingId)
    if(meeting != null) DetailContent(
        meeting,
        isJoined,
        {viewModel.onEvent(HomeEvent.JoinOrLeaveEvent(meeting.id))},
        { navController.popBackStack() })
}

@Composable
fun DetailContent(
    meeting : Meeting,
    isJoined : Boolean,
    onJoin : () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { TopBar(
            title = "Detalles de la reunion",
            showBack = true,
            onBack = onBack
        ) }, containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.95f)
    ) {
        Column(modifier = Modifier
            .padding(it)
            .padding(horizontal = 8.dp)
            .padding(bottom = 128.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.Center) {
            MeetingFullCard(
                meeting = meeting,
                isJoined = isJoined,
                onJoin = onJoin
            )

        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    ConectaTheme(dynamicColor = false) {
        DetailContent(
            Meeting(
                date = LocalDate.now(),
                startTime = LocalTime.now(),
                endTime = LocalTime.now(),
                maxStudents = 4,
                title = "Reunion sobre analisis, nos estamos preparando para el final de marzo",
                studyPlace = StudyPlace(
                    id = null,
                    location = "Biblioteca",
                    isVirtual = false,
                    details = "Mesa grande"
                ),
                subject = Subject(
                    1, "Analisis Matematico"
                ),
                id = 1,
                studentsNumber = 1
            ),
            true, {}, {}
        )
    }
}