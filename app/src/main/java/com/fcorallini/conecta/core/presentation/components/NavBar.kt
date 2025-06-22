package com.fcorallini.conecta.core.presentation.components


import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fcorallini.conecta.navigation.Routes

@Composable
fun NavBar(navController: NavController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    val items = listOf(
        NavItem("Home", Icons.Outlined.Home, Routes.Home),
        NavItem("Calendario", Icons.Outlined.DateRange, Routes.Home),
        NavItem("Crear", Icons.Outlined.AddCircle, Routes.Create),
        NavItem("Mensajes", Icons.Outlined.MailOutline, Routes.Home),
        NavItem("Perfil", Icons.Outlined.AccountCircle, Routes.Profile),
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        items.forEach { item ->
            val selected = currentDestination == item.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
            )
        }
    }
}

data class NavItem(val label: String, val icon: ImageVector, val route: Any)
