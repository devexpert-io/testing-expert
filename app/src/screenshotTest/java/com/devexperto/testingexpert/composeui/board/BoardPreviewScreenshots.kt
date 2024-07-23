package com.devexperto.testingexpert.composeui.board

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devexperto.testingexpert.domain.GameState
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.move
import com.devexperto.testingexpert.ui.board.BoardViewModel

class BoardPreviewScreenshots {

    @Preview(showBackground = true)
    @Composable
    fun BoardViewPreview() {
        val ticTacToe = TicTacToe()
            .move(0, 0)
            .move(1, 1)

        val state = BoardViewModel.UiState(
            ticTacToe = ticTacToe,
            gameState = GameState.InProgress
        )

        Board(
            state = state,
            onStartClick = { },
            onMove = { _, _ -> },
            onPlayAgainClick = { }
        )
    }

}