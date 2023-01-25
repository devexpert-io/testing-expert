package com.devexperto.testingexpert.composeui.games

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
object Games

@Composable
fun Games() {
    Text(text = "Game")
}