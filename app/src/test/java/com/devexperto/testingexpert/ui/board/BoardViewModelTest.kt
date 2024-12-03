package com.devexperto.testingexpert.ui.board

import com.devexperto.testingexpert.domain.GameState
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.testrules.CoroutinesTestRule
import com.devexperto.testingexpert.usecases.AddScoreUseCase
import com.devexperto.testingexpert.usecases.GetCurrentBoardUseCase
import com.devexperto.testingexpert.usecases.MakeBoardMoveUseCase
import com.devexperto.testingexpert.usecases.ResetBoardUseCase
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BoardViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var makeBoardMoveUseCase: MakeBoardMoveUseCase

    @MockK
    lateinit var getCurrentBoardUseCase: GetCurrentBoardUseCase

    @MockK
    lateinit var addScoreUseCase: AddScoreUseCase

    @MockK
    lateinit var resetBoardUseCase: ResetBoardUseCase

    lateinit var viewModel: BoardViewModel

    @Before
    fun setUp() {
        every { getCurrentBoardUseCase() } returns flowOf(TicTacToe())
        viewModel = BoardViewModel(
            makeBoardMoveUseCase,
            getCurrentBoardUseCase,
            addScoreUseCase,
            resetBoardUseCase
        )
    }

    @Test
    fun `at the beginning, the game is not started`() = runTest {
        assertEquals(GameState.NotStarted, viewModel.state.value.gameState)
    }

    @Test
    fun `when start game is called, game state is in progress`() = runTest {
        viewModel.startGame()

        runCurrent()

        assertEquals(GameState.InProgress, viewModel.state.value.gameState)
    }

    @Test
    fun `when reset is called, then the game is cleared`() = runTest {
        coJustRun { resetBoardUseCase() }

        viewModel.resetGame()

        runCurrent()

        coVerify { resetBoardUseCase() }
    }

    @Test
    fun `move is recorded by use case`() = runTest {

        val slot1 = slot<Int>()
        val slot2 = slot<Int>()
        coJustRun { makeBoardMoveUseCase(capture(slot1), capture(slot2)) }

        viewModel.move(0, 1)

        runCurrent()

        assertEquals(0, slot1.captured)
        assertEquals(1, slot2.captured)
    }

}