package com.devexperto.testingexpert.composeui.scoreboard

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
object Scoreboard

@Composable
fun Scoreboard() {
    Text(text = "Scoreboard")
}