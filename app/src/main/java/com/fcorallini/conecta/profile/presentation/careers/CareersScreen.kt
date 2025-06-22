package com.fcorallini.conecta.profile.presentation.careers

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
fun CareersScreen(
    viewModel : CareersViewModel = hiltViewModel(),
    navController: NavController
) {
    CareersContent(viewModel.state, viewModel::onEvent, navController)
}

@Composable
fun CareersContent(
    state : CareersState,
    onEvent: (CareerEvent) -> Unit,
    navController: NavController
){
    Scaffold(
        topBar = { TopBar(
            title = "Mis Carreras",
            showBack = true,
            onBack = { navController.popBackStack() }
        ) },
        bottomBar = { NavBar(navController) }
    ) {
        LazyColumn(modifier = Modifier
            .padding(it)
            .padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.curriculumList) {
                ProfileItem(
                    id = it.id,
                    name = it.name,
                    selectedIds = state.selectedCareers,
                    onItemClick = { onEvent(CareerEvent.FollowCareer(it.id)) },
                )
            }
        }
    }
}
