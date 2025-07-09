package com.fcorallini.conecta.core.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    var showRow by remember { mutableStateOf(true) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .clickable { showRow = !showRow }
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(14.dp)
            ) {
                Text(
                    text = meeting.subject.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            if(showRow) Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.Create, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text(text = meeting.title)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.Place, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text(text = meeting.studyPlace.location)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text(text = meeting.date.toString().split("-").reversed().joinToString("/"))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.PlayArrow, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text(text = meeting.startTime.toString().take(5) + "Hs - " + meeting.endTime.toString().take(5) + "Hs")
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(end = 12.dp).height(120.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    TextButton(
                        onClick = onJoin,
                        modifier = Modifier.width(92.dp),
                        shape = CircleShape,
                        colors = buttonColors
                    ) {
                        Text(text = buttonLabel)
                    }
                }
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