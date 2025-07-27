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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
            containerColor = MaterialTheme.colorScheme.error
        )
    }

    var expand by remember { mutableStateOf(!isJoined) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
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
                    .clickable { expand = !expand }
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .padding(12.dp)
            ) {
                Text(
                    text = meeting.subject.name,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
                    fontWeight = FontWeight(450),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                if (isJoined) Icon(
                    imageVector = Icons.Outlined.ThumbUp,
                    contentDescription = null,
                    modifier.align(Alignment.CenterEnd),
                    tint = MaterialTheme.colorScheme.onPrimary)
            }
            if(expand) Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, bottom = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.Create, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text(text = meeting.title, maxLines = 1, fontSize = 14.sp)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = meeting.date.toString().split("-").reversed().joinToString("/") + "  -  " +
                                    meeting.startTime.toString().take(5) + "Hs",
                            fontSize = 14.sp
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.Place, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text(text = meeting.studyPlace.location, fontSize = 14.sp)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text(text = meeting.studentsNumber.toString() + " / " + meeting.maxStudents.toString(), fontSize = 14.sp)
                    }
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Icon(imageVector = Icons.Outlined.PlayArrow, contentDescription = null)
//                        Spacer(Modifier.width(6.dp))
//                        Text(text = meeting.startTime.toString().take(5) + "Hs - " + meeting.endTime.toString().take(5) + "Hs", fontSize = 14.sp)
//                    }
                }

                Column(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .height(116.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    TextButton(
                        onClick = {
                            onJoin()
                            expand = !expand
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
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewMeetingCard() {
    ConectaTheme {
        MeetingFullCard(
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
                id = 0,
                studentsNumber = 1
            ) ,
            onJoin = {},
            isJoined = false
        )
    }
}