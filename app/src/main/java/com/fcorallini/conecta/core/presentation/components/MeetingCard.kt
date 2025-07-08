package com.fcorallini.conecta.core.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.core.presentation.theme.ConectaTheme
import java.time.LocalDate
import java.time.LocalTime


@Composable
fun MeetingCard(
    meeting: Meeting,
    modifier: Modifier = Modifier,
    onJoin : () -> Unit,
    onClick : () -> Unit = {},
    isJoined : Boolean,
) {
    val buttonLabel = if (isJoined) "Abandonar" else "Unirme"
    val buttonColors = if(!isJoined) {
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onErrorContainer
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = meeting.subject.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = meeting.studyPlace.location,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight(400),
                maxLines = 1
            )
            Text(
                text = meeting.startTime.toString().take(5).plus("Hs"),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight(400),
                maxLines = 1
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Button(
                onClick = onJoin,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                colors = buttonColors

            ) {
                Text(
                    text = buttonLabel,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight(400)
                )
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewMeetingCard() {
    ConectaTheme {
        MeetingCard(
            meeting = Meeting(
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
            ) ,
            onJoin = {},
            isJoined = true
        )
    }
}