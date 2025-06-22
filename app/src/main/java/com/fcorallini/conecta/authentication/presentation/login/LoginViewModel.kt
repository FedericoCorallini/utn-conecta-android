package com.fcorallini.conecta.authentication.presentation.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.auth0.android.result.Credentials
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.provider.WebAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.auth0.android.callback.Callback
import com.fcorallini.conecta.authentication.domain.usecases.SaveJwtTokenUseCase
import kotlinx.coroutines.launch


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveJwtTokenUseCase: SaveJwtTokenUseCase
) : ViewModel(){

    var token by mutableStateOf("")
        private set

    private val account = Auth0(
        "RNIxDz64G7qNKKiLReFaYJ3BJEJxYYa3",
        "dev-a8n8uoz6yo02wcvy.us.auth0.com"
    )


    fun loginWithBrowser(context: Context, navigate : () -> Unit) {
        val callback = object : Callback<Credentials, AuthenticationException> {
            override fun onFailure(exception: AuthenticationException) {
            }

            override fun onSuccess(credentials: Credentials) {
                val accessToken = credentials.accessToken
                token = accessToken
                Log.d("token", accessToken)
                viewModelScope.launch {
                    saveJwtTokenUseCase(accessToken)
                    navigate()
                }
            }
        }
        WebAuthProvider.login(account)
            .withScheme("demo")
            .withScope("openid profile email")
            .withAudience("http://utn-conecta")
            .start(context, callback)
    }

    fun logout(context: Context) {
        WebAuthProvider.logout(account)
            .withScheme("demo")
            .start(context, object: Callback<Void?, AuthenticationException> {
                override fun onSuccess(payload: Void?) {
                }
                override fun onFailure(error: AuthenticationException) {
                }
            })
    }
}