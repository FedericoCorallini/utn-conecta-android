package com.fcorallini.conecta.profile.presentation.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fcorallini.conecta.core.presentation.components.NavBar
import com.fcorallini.conecta.core.presentation.components.TopBar
import com.fcorallini.conecta.navigation.Routes
import com.fcorallini.conecta.profile.presentation.menu.components.MenuItem

@Composable
fun MenuScreen(
    navController: NavController
) {
    MenuContent(navController)
}

@Composable
fun MenuContent(
    navController: NavController
){
    Scaffold(
        topBar = { TopBar(
            title = "Perfil"
        ) },
        bottomBar = { NavBar(navController) }
    ) {
        Column(modifier = Modifier.padding(it).padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            MenuItem(
                text = "Mis carreras",
                icon = Icons.Default.Star,
                onClick = { navController.navigate(Routes.Careers) }
            )
            MenuItem(
                text = "Mis materias",
                icon = Icons.Default.FavoriteBorder,
                onClick = { navController.navigate(Routes.Subjects) }
            )
            MenuItem(
                text = "Logout",
                icon = Icons.Default.ExitToApp,
                onClick = {  }
            )
        }
    }
}

@Composable
@Preview
fun PreviewMenu() {
    MenuContent(rememberNavController())
}
