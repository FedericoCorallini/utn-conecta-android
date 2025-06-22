package com.fcorallini.conecta.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fcorallini.conecta.core.presentation.theme.ConectaTheme
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime


@Composable
fun ProfileItem(
    id : Long,
    name : String,
    selectedIds : List<Long>,
    onItemClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    val selected : Boolean = selectedIds.contains(id)
    Row(
        modifier = modifier.fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if(selected) Color.Blue.copy(alpha = 0.2f) else Color.White)
            .clickable { onItemClick() }
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name)
        if (selected) Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHabit() {
    ConectaTheme(dynamicColor = false) {
        ProfileItem(
            1,
            "Software Engineering:\nSoftware analist curriculum",
            selectedIds = listOf(1,3,5),
            {},
        )
    }
}