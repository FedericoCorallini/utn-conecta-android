package com.fcorallini.conecta.profile.presentation.menu

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fcorallini.conecta.authentication.presentation.login.LoginViewModel
import com.fcorallini.conecta.core.presentation.components.NavBar
import com.fcorallini.conecta.core.presentation.components.TopBar
import com.fcorallini.conecta.navigation.Routes
import com.fcorallini.conecta.profile.presentation.menu.components.MenuItem

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = hiltViewModel(),
    navController: NavController
) {
    MenuContent(viewModel::logout, navController)
}

@Composable
fun MenuContent(
    logout : (Context, () -> Unit) -> Unit,
    navController: NavController
){
    val context = LocalContext.current
    Scaffold(
        topBar = { TopBar(
            title = "Edita tu perfil"
        ) },
        bottomBar = { NavBar(navController) }
    ) {
        Column(modifier = Modifier.padding(it).padding(horizontal = 4.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            MenuItem(
                text = "Mis carreras",
                icon = Icons.Outlined.Star,
                onClick = { navController.navigate(Routes.Careers) }
            )
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.padding(horizontal = 12.dp))
            MenuItem(
                text = "Mis materias",
                icon = Icons.Default.FavoriteBorder,
                onClick = { navController.navigate(Routes.Subjects) }
            )
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.padding(horizontal = 12.dp))
            MenuItem(
                text = "Logout",
                icon = Icons.Default.ExitToApp,
                onClick = {
                    logout(context) {
                        navController.navigate(Routes.Login) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}
