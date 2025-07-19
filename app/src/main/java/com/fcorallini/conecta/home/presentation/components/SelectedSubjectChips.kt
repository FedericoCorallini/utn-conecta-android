package com.fcorallini.conecta.home.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fcorallini.conecta.core.domain.model.Subject

@Composable
fun SelectedSubjectChips(
    selectedSubjects: List<Subject>,
    onSubjectChange: (Subject) -> Unit,
) {
    LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
        items(selectedSubjects) { subject ->
            var selected by remember { mutableStateOf(true) }
            FilterChip(
                onClick = { selected = !selected },
                label = {
                    Text(subject.name)
                },
                selected = selected,
                leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                ,
            )
        }
    }
}
