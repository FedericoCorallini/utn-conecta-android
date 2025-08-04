package com.fcorallini.conecta.core.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fcorallini.conecta.core.domain.model.Meeting
import com.fcorallini.conecta.core.domain.model.StudyPlace
import com.fcorallini.conecta.core.domain.model.Subject
import com.fcorallini.conecta.core.presentation.theme.ConectaTheme
import java.time.LocalDate
import java.time.LocalTime


@Composable
fun MeetingFullCard(
    meeting: Meeting,
    modifier: Modifier = Modifier,
    onJoin : () -> Unit,
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
            containerColor = MaterialTheme.colorScheme.error
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .padding(vertical = 12.dp)
            ) {
                Text(
                    text = meeting.subject.name,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
                    fontWeight = FontWeight(450),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = modifier.align(Alignment.Center)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp).horizontalScroll(
                rememberScrollState()
            )) {
                Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null)
                Spacer(Modifier.width(6.dp))
                Column {
                    Text(
                        text = meeting.date.toString().split("-").reversed().joinToString("/"),
                        fontSize = 14.sp
                    )
                    Text(text = meeting.startTime.toString().take(5) + " - " + meeting.endTime.toString().take(5) + " Hs", fontSize = 14.sp)
                }

            }
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp).horizontalScroll(
                rememberScrollState()
            )) {
                Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = null)
                Spacer(Modifier.width(6.dp))
                Column {
                    Text(text = meeting.studyPlace.location, maxLines = 1, fontSize = 14.sp)
                    Text(text = meeting.studyPlace.details, maxLines = 1, fontSize = 14.sp)
                }

            }
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp).horizontalScroll(
                rememberScrollState()
            )) {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
                Spacer(Modifier.width(6.dp))
                Column {
                    Text(text = meeting.studentsNumber.toString() + " / " + meeting.maxStudents.toString(), fontSize = 14.sp)

                }

            }

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(48.dp)) {
                Icon(imageVector = Icons.Outlined.Create, contentDescription = null)
                Spacer(Modifier.width(6.dp))
                Text(text = meeting.title, maxLines = 2, fontSize = 14.sp)
            }
            Row(modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.Center) {
                TextButton(
                    onClick = {
                        onJoin()
                    },
                    modifier = Modifier.width(92.dp),
                    shape = CircleShape,
                    colors = buttonColors,
                    enabled = !((!isJoined) && (meeting.studentsNumber == meeting.maxStudents))
                ) {
                    Text(text = buttonLabel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewMeetingFullCard() {
    ConectaTheme {
        MeetingFullCard(
            meeting = Meeting(
                date = LocalDate.now(),
                startTime = LocalTime.now(),
                endTime = LocalTime.now().plusHours(2),
                maxStudents = 4,
                title = "En esta reunion se van a estudiar un monton de temas " +
                        "variados poruqe estamos pensando en prepara el final ",
                studyPlace = StudyPlace(
                    id = null,
                    location = "ZOOM",
                    isVirtual = false,
                    details = "Unete para ver el link"
                ),
                subject = Subject(
                    1, "Analisis Matematico"
                ),
                id = 0,
                studentsNumber = 1
            ) ,
            onJoin = {},
            isJoined = false
        )
    }
}