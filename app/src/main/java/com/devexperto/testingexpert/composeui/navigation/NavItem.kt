package com.devexperto.testingexpert.composeui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.ui.graphics.vector.ImageVector
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.composeui.board.Board
import com.devexperto.testingexpert.composeui.games.Games
import com.devexperto.testingexpert.composeui.scoreboard.Scoreboard

enum class NavItem(val route: Any, val title: Int, val icon: ImageVector) {
    BOARD(Board, R.string.game, Icons.Default.Games),
    SCOREBOARD(Scoreboard, R.string.scoreboard, Icons.Default.Scoreboard),
    GAMES(Games, R.string.other_games, Icons.Default.VideogameAsset)
}