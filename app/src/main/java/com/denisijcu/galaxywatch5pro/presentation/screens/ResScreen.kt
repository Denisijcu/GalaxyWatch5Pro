package com.denisijcu.galaxywatch5pro.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Text
import com.denisijcu.galaxywatch5pro.presentation.navigation.NavRoutes


@Composable
fun ResScreen(navController: NavHostController, response:String?) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .background(color = Color.Black), contentAlignment = Alignment.TopCenter) {

        Column {
            Text(text = "Traduccion: $response")

            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Button(onClick = {
                    navController.navigate(NavRoutes.Chat.route)
                }) {
                    Text(text = "Back")

                }
            }
        }
    }
}