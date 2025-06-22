package com.fcorallini.conecta.creation.presentation

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fcorallini.conecta.core.presentation.components.DatePickerField
import com.fcorallini.conecta.core.presentation.components.DropdownSelector
import com.fcorallini.conecta.core.presentation.components.NavBar
import com.fcorallini.conecta.core.presentation.components.TimePickerField
import com.fcorallini.conecta.core.presentation.components.TopBar
import com.fcorallini.conecta.core.presentation.theme.ConectaTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateScreen(
    viewModel: CreateViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val toastMessage = viewModel.state.toastMessage

    LaunchedEffect(viewModel.state.toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    if(viewModel.state.locations.isNotEmpty() && viewModel.state.subjectList.isNotEmpty()){
        CreateContent(
            viewModel.state,
            viewModel::onEvent,
            navController
        )
    }
    else{
        Text("Loading data")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateContent(
    state: CreateState,
    onEvent : (CreateEvent) -> Unit,
    navController: NavController
) {
    Scaffold(
        topBar = { TopBar(
            title = "Home"
        ) },
        bottomBar = { NavBar(navController) }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DropdownSelector(
                label = "Materia",
                options = state.subjectList.map { it.name },
                selectedOption = state.subjectName,
                onOptionSelected = { onEvent(CreateEvent.SubjectNameChanged(it))}
            )

            OutlinedTextField(
                value = state.title,
                onValueChange = { onEvent(CreateEvent.TitleChanged(it)) },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.titleErrorMessage != null,
            )
            if (state.titleErrorMessage != null) {
                Text(text = state.titleErrorMessage, color = MaterialTheme.colorScheme.error)
            }

            OutlinedTextField(
                value = state.maxStudents.toString(),
                onValueChange = {
                    it.toIntOrNull()?.let { value ->
                        onEvent(CreateEvent.MaxStudentsChanged(value))
                    }
                },
                label = { Text("Máximo de estudiantes") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.maxStudentsErrorMessage != null,
            )
            if (state.maxStudentsErrorMessage != null) {
                Text(text = state.maxStudentsErrorMessage, color = MaterialTheme.colorScheme.error)
            }

            DropdownSelector(
                label = "Lugar de estudio",
                options = state.locations.map { it.location },
                selectedOption = state.studyPlaceLocationName,
                onOptionSelected = { onEvent(CreateEvent.StudyPlaceLocationNameChanged(it))}
            )

            OutlinedTextField(
                value = state.studyPlaceDetails,
                onValueChange = { onEvent(CreateEvent.StudyPlaceDetailsChanged(it)) },
                label = { Text("Detalles del lugar") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.studyPlaceDetailsErrorMessage != null,
            )
            if (state.studyPlaceDetailsErrorMessage != null) {
                Text(text = state.studyPlaceDetailsErrorMessage, color = MaterialTheme.colorScheme.error)
            }

            DatePickerField(
                date = state.date,
                onDateChange = {onEvent(CreateEvent.DateChanged(it))}
            )
            if (state.dateErrorMessage != null) {
                Text(text = state.dateErrorMessage, color = MaterialTheme.colorScheme.error)
            }


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)){
                TimePickerField(
                    time = state.startTime,
                    onTimeChange = {onEvent(CreateEvent.StartTimeChanged(it))},
                    label = "Desde",
                    modifier = Modifier.weight(0.5f)
                )

                TimePickerField(
                    time = state.endTime,
                    onTimeChange = {onEvent(CreateEvent.EndTimeChanged(it))},
                    label = "Hasta",
                    modifier = Modifier.weight(0.5f)
                )
            }
            if (state.timeErrorMessage != null) {
                Text(text = state.timeErrorMessage, color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = { onEvent(CreateEvent.Submit) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Crear")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewCreateScreen() {
    ConectaTheme {
        CreateContent(
            CreateState(),
            {},
            rememberNavController()
        )
    }
}
