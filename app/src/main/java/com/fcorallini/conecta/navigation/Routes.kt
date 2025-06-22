package com.fcorallini.conecta.navigation

import kotlinx.serialization.Serializable

interface Routes {

    @Serializable
    object Home : Routes

    @Serializable
    object Login : Routes

    @Serializable
    object Create : Routes

    @Serializable
    object Profile : Routes

    @Serializable
    object Careers : Routes

    @Serializable
    object Subjects : Routes
}