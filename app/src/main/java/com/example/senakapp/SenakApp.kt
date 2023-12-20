package com.example.senakapp

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.senakapp.ui.navigation.BottomBar
import com.example.senakapp.ui.screen.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SenakApp() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }

    ) {
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root
        )
    }
}
