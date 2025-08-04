package com.fcorallini.conecta.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.vector.ImageVector
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
    imageVector: ImageVector,
    modifier: Modifier = Modifier
) {
    val selected : Boolean = selectedIds.contains(id)
    Row(
        modifier = modifier.fillMaxWidth()
            .height(58.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if(selected) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp)
            )
            .border(width = 1.5.dp, color =
                if(selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(8.dp))
            .clickable { onItemClick() }
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            color = if(selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.secondary
        )
        if (selected) Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHabit() {
    ConectaTheme(dynamicColor = false) {
        ProfileItem(
            1,
            "Software Engineering",
            selectedIds = listOf(2,3,5),
            {},
            Icons.Default.Check
        )
    }
}