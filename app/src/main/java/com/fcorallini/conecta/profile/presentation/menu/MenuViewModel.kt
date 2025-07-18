package com.fcorallini.conecta.profile.presentation.menu

import android.content.Context
import androidx.lifecycle.ViewModel
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel(){
    private val account = Auth0(
        "RNIxDz64G7qNKKiLReFaYJ3BJEJxYYa3",
        "dev-a8n8uoz6yo02wcvy.us.auth0.com"
    )
    fun logout(context: Context, onComplete: () -> Unit) {
        WebAuthProvider.logout(account)
            .withScheme("demo")
            .start(context, object: Callback<Void?, AuthenticationException> {
                override fun onSuccess(payload: Void?) {
                    onComplete()
                }
                override fun onFailure(error: AuthenticationException) {
                }
            })
    }
}