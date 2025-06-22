package com.fcorallini.conecta.profile.presentation.subjects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fcorallini.conecta.core.presentation.components.NavBar
import com.fcorallini.conecta.core.presentation.components.TopBar
import com.fcorallini.conecta.profile.presentation.components.ProfileItem


@Composable
fun SubjectScreen(
    viewModel : SubjectsViewModel = hiltViewModel(),
    navController: NavController
) {
    SubjectsContent(viewModel.state, viewModel::onEvent, navController)
}

@Composable
fun SubjectsContent(
    state : SubjectsState,
    onEvent: (SubjectsEvent) -> Unit,
    navController: NavController
){
    Scaffold(
        topBar = { TopBar(
            title = "Mis Materias",
            showBack = true,
            onBack = { navController.popBackStack() }
        ) },
        bottomBar = { NavBar(navController) }
    ) {
        LazyColumn(modifier = Modifier
            .padding(it)
            .padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.subjects) {
                ProfileItem(
                    id = it.id,
                    name = it.name,
                    selectedIds = state.followedSubjectsIds,
                    onItemClick = { onEvent(SubjectsEvent.Follow(it.id)) },
                )
            }
        }
    }
}
