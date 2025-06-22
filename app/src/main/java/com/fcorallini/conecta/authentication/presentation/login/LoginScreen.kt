package com.fcorallini.conecta.authentication.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigate : () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(Unit){
        //viewModel.logout(context = context)
        viewModel.loginWithBrowser(context = context, navigate = onNavigate)
    }

//    Column {
//        Button(onClick = {viewModel.loginWithBrowser(context = context, navigate = onNavigate )}) {
//            Text(
//                text = "Login"
//            )
//        }
//        Button(onClick = {viewModel.logout(context = context)}) {
//            Text(
//                text = "Logout"
//            )
//        }
//        Text(text = viewModel.token)
//    }
}