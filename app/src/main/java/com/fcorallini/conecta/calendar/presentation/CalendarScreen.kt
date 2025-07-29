package com.fcorallini.conecta.calendar.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fcorallini.conecta.core.presentation.components.NavBar
import com.fcorallini.conecta.core.presentation.components.TopBar
import com.fcorallini.conecta.core.presentation.theme.ConectaTheme
import com.fcorallini.conecta.core.domain.model.Meeting
import java.time.LocalDate
import java.time.LocalTime
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.fcorallini.conecta.core.domain.repository.CoreRepository
import com.fcorallini.conecta.core.domain.usecases.GetUserMeetingsUseCase
import com.fcorallini.conecta.core.presentation.components.DatePickerField

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(
    navController: NavController,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val meetings by viewModel.meetings.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMeetings()
    }

    val today = remember { LocalDate.now() }
    var selectedDate by remember { mutableStateOf(today) }
    var selectedMeeting by remember { mutableStateOf<Meeting?>(null) }

    val meetingsForSelectedDate = meetings.filter { it.date == selectedDate }

    Scaffold(
        topBar = { TopBar(title = "Calendario") },
        bottomBar = { NavBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            DatePickerField(
                date = selectedDate,
                onDateChange = { selectedDate = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (meetingsForSelectedDate.isEmpty()) {
                Text("No hay reuniones para esta fecha.")
            } else {
                LazyColumn {
                    items(meetingsForSelectedDate) { meeting ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { selectedMeeting = meeting }
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(meeting.title, style = MaterialTheme.typography.titleMedium)
                                Text("Hora: ${meeting.startTime} - ${meeting.endTime}")
                                Text("Lugar: ${meeting.studyPlace.location}")
                            }
                        }
                    }
                }
            }
        }

        selectedMeeting?.let { meeting ->
            AlertDialog(
                onDismissRequest = { selectedMeeting = null },
                confirmButton = {
                    TextButton(onClick = { selectedMeeting = null }) {
                        Text("Cerrar")
                    }
                },
                title = { Text(meeting.title) },
                text = {
                    Column {
                        Text("Materia: ${meeting.subject.name}")
                        Text("Fecha: ${meeting.date}")
                        Text("Horario: ${meeting.startTime} - ${meeting.endTime}")
                        Text("Lugar: ${meeting.studyPlace.location}")
                    }
                }
            )
        }
    }
}
