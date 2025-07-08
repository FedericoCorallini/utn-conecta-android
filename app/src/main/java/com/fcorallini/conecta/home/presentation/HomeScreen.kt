package com.fcorallini.conecta.home.presentation

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.core.presentation.components.MeetingCard
import com.fcorallini.conecta.core.presentation.components.NavBar
import com.fcorallini.conecta.core.presentation.components.TopBar
import com.fcorallini.conecta.core.presentation.theme.ConectaTheme
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
            title = "Home"
        ) },
        bottomBar = { NavBar(navController) }
    ) {
        LazyColumn(modifier = Modifier.padding(it).padding(horizontal = 8.dp)) {

            item {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Buscar materia...") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {  }
                    ),
                    modifier = Modifier
                        .fillMaxWidth().padding(horizontal = 8.dp).padding(bottom = 12.dp),
                    shape = CircleShape
                )
            }
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
                        title = "Analisis Matematico",
                        studyPlace = StudyPlace(
                            id = null,
                            location = "Biblioteca",
                            isVirtual = false
                        ),
                        subject = Subject(
                            1, "Analisis Matematico"
                        ),
                        id = 0
                    ),
                    Meeting(
                        date = LocalDate.now(),
                        startTime = LocalTime.now(),
                        endTime = LocalTime.now(),
                        maxStudents = 4,
                        title = "Analisis de Sistemas",
                        studyPlace = StudyPlace(
                            id = null,
                            location = "Biblioteca",
                            isVirtual = false
                        ),
                        subject = Subject(
                            1,"Analisis Matematico"
                        ),
                        id = 0
                    )
                )
            ),
            {},
            rememberNavController()
        )
    }
}