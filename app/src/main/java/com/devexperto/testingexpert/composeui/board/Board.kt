package com.devexperto.testingexpert.composeui.board

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
object Board

@Composable
fun Board() {
    Text(text = "Board")
}