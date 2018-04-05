package com.hossynohito.retrofit2kotlincoroutinessample.presentation.boarddetail

import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Board
import com.hossynohito.retrofit2kotlincoroutinessample.domain.usecase.BoardUseCase
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class BoardDetailPresenter @Inject constructor(
        private val view: BoardDetailActivity,
        private val board: Board,
        private val useCase: BoardUseCase
) {

    private val job = Job()

    fun onViewCreated() {
        launch(job + UI) {
            loadPipelines()
        }
    }

    fun onDestroy() {
        job.cancel()
    }

    private suspend fun loadPipelines() {
        try {
            useCase.getPipelines(board.id).let(view::setViewPager)
        } catch (t: Throwable) {
            t.message?.let(view::showErrorToast)
        }
    }
}