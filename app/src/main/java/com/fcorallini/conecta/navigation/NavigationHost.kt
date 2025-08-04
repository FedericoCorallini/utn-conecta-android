package com.fcorallini.conecta.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fcorallini.conecta.creation.presentation.CreateScreen
import com.fcorallini.conecta.home.presentation.home.HomeScreen
import com.fcorallini.conecta.authentication.presentation.login.LoginScreen
import com.fcorallini.conecta.home.presentation.detail.DetailScreen
import com.fcorallini.conecta.home.presentation.home.HomeViewModel
import com.fcorallini.conecta.profile.presentation.careers.CareersScreen
import com.fcorallini.conecta.profile.presentation.menu.MenuScreen
import com.fcorallini.conecta.profile.presentation.subjects.SubjectScreen
import com.fcorallini.conecta.calendar.presentation.CalendarScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationHost(startDestination : Routes = Routes.Login) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination){
        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }
        composable<Routes.Detail> { backStackEntry ->
            val viewModel: HomeViewModel =
                if (navController.previousBackStackEntry != null) hiltViewModel(
                    navController.previousBackStackEntry!!
                ) else hiltViewModel()
            val detailRoute : Routes.Detail = backStackEntry.toRoute()
            DetailScreen(detailRoute.id, navController, viewModel)

        }
        composable<Routes.Login> {
            LoginScreen() {
                navController.navigate(Routes.Home)
            }
        }
        composable<Routes.Create> {
            CreateScreen(navController = navController)
        }
        composable<Routes.Profile> {
            MenuScreen(navController = navController)
        }
        composable<Routes.Careers> {
            CareersScreen(navController = navController)
        }
        composable<Routes.Subjects> {
            SubjectScreen(navController = navController)
        }
        composable<Routes.Calendar> {
            CalendarScreen(navController = navController)
        }
    }
}
