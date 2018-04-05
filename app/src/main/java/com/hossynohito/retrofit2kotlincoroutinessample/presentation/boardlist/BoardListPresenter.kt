package com.hossynohito.retrofit2kotlincoroutinessample.presentation.boardlist

import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Board
import com.hossynohito.retrofit2kotlincoroutinessample.domain.usecase.BoardUseCase
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class BoardListPresenter @Inject constructor(
        private val view: BoardListActivity,
        private val useCase: BoardUseCase
) {

    private val job = Job()

    fun onViewCreated() {
        launch(job + UI) { loadPipelines() }
    }

    fun onDestroy() {
        job.cancel()
    }

    fun onBoardClick(board: Board) {
        view.navigateToBoardDetail(board)
    }

    private suspend fun loadPipelines() {
        try {
            useCase.getMyBoards().let(view::addBoards)
        } catch (t: Throwable) {
            t.message?.let(view::showErrorToast)
        }
    }
}