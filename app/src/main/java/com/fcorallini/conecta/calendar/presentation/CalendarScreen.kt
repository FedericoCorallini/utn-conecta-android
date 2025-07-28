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
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import java.time.LocalDate
import java.time.LocalTime
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.fcorallini.conecta.core.presentation.components.DatePickerField

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(navController: NavController) {
    val today = remember { LocalDate.now() }
    var selectedDate by remember { mutableStateOf(today) }
    var selectedMeeting by remember { mutableStateOf<Meeting?>(null) }

    val allMeetings = remember {
        listOf(
            Meeting(
                id = 1,
                title = "Grupo de Álgebra",
                date = today,
                startTime = LocalTime.of(18, 0),
                endTime = LocalTime.of(19, 0),
                maxStudents = 6,
                subject = Subject(1, "Álgebra"),
                studyPlace = StudyPlace(null, "Aula 201", false)
            ),
            Meeting(
                id = 2,
                title = "Grupo de Física",
                date = today.plusDays(1),
                startTime = LocalTime.of(14, 0),
                endTime = LocalTime.of(15, 30),
                maxStudents = 4,
                subject = Subject(2, "Física"),
                studyPlace = StudyPlace(null, "Biblioteca", false)
            )
        )
    }

    val meetingsForSelectedDate = allMeetings.filter { it.date == selectedDate }

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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewCalendarScreen() {
    ConectaTheme {
        CalendarScreen(navController = rememberNavController())
    }
}
