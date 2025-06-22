package com.fcorallini.conecta

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.fcorallini.conecta.navigation.NavigationHost
import com.fcorallini.conecta.core.presentation.theme.ConectaTheme
import dagger.hilt.android.AndroidEntryPoint
import com.auth0.android.Auth0

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val account = Auth0(
            BuildConfig.AUTH_CLIENT_ID,
            BuildConfig.AUTH_DOMAIN
        )

        enableEdgeToEdge()
        setContent {
            ConectaTheme {
                NavigationHost()
            }
        }
    }
}

