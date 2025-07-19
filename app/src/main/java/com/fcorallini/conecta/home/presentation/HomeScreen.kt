package com.fcorallini.conecta.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fcorallini.conecta.R
import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.core.presentation.components.MeetingCard
import com.fcorallini.conecta.core.presentation.components.NavBar
import com.fcorallini.conecta.core.presentation.components.TopBar
import com.fcorallini.conecta.core.presentation.theme.ConectaTheme
import com.fcorallini.conecta.home.presentation.components.SelectedSubjectChips
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    HomeContent(viewModel.state, viewModel::onEvent, navController)
}

@Composable
fun HomeContent(
    state: HomeState,
    event: (HomeEvent) -> Unit,
    navController: NavController
) {
    Scaffold(
        topBar = { TopBar(
            title = "Reuniones propuestas"
        ) },
        bottomBar = { NavBar(navController) }
    ) {
        LazyColumn(modifier = Modifier.padding(it).padding(horizontal = 8.dp, vertical = 4.dp)) {
            items(state.meetingList) { meeting ->
                MeetingCard(
                    meeting = meeting,
                    isJoined = state.joinedMeetingIds.contains(meeting.id),
                    onJoin = {event.invoke(HomeEvent.JoinOrLeaveEvent(meeting.id))}
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    ConectaTheme {
        HomeContent(
            state = HomeState(
                meetingList = listOf(
                    Meeting(
                        date = LocalDate.now(),
                        startTime = LocalTime.now(),
                        endTime = LocalTime.now(),
                        maxStudents = 4,
                        title = "Reunion sobre analisis",
                        studyPlace = StudyPlace(
                            id = null,
                            location = "Biblioteca",
                            isVirtual = false
                        ),
                        subject = Subject(
                            1, "Analisis Matematico"
                        ),
                        id = 1,
                        studentsNumber = 1
                    ),
                    Meeting(
                        date = LocalDate.now(),
                        startTime = LocalTime.now(),
                        endTime = LocalTime.now(),
                        maxStudents = 4,
                        title = "Para el final y que pasas si aca le pongo un titulo recontra largo y te rompo toda la ui",
                        studyPlace = StudyPlace(
                            id = null,
                            location = "Biblioteca",
                            isVirtual = false
                        ),
                        subject = Subject(
                            1,"Analisis Matematico"
                        ),
                        id = 0,
                        studentsNumber = 1
                    )
                ), joinedMeetingIds = listOf(1)
            ),
            {},
            rememberNavController()
        )
    }
}