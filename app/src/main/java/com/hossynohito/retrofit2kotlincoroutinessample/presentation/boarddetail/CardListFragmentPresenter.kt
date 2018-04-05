package com.hossynohito.retrofit2kotlincoroutinessample.presentation.boarddetail

import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Board
import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Card
import com.hossynohito.retrofit2kotlincoroutinessample.domain.usecase.BoardUseCase
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class CardListFragmentPresenter @Inject constructor(
        private val view: CardListFragment,
        private val pipelineId: String,
        private val useCase: BoardUseCase
) {

    private val job = Job()

    fun onViewCreated() {
        launch(job + UI) {
            loadCards()
        }
    }

    fun onDestroy() {
        job.cancel()
    }

    fun onAddButtonClick(text: String) {
        launch(job + UI) {
            addCard(text)
        }
    }

    fun  onCardLongClick(card: Card) {
        launch(job + UI) {
            deleteCard(card.id)
        }
    }

    private suspend fun loadCards() {
        try {
            useCase.getCards(pipelineId).let(view::addCards)
        } catch (t: Throwable) {
            t.message?.let(view::showErrorToast)
        }
    }

    private suspend fun addCard(text: String) {
        try {
            useCase.addCard(pipelineId, text).let(view::addCard)
        } catch (t: Throwable) {
            t.message?.let(view::showErrorToast)
        }
    }

    private suspend fun deleteCard(cardId: String) {
        try {
            useCase.deleteCard(cardId)
            view.removeCard(cardId)
        } catch (t: Throwable) {
            t.message?.let(view::showErrorToast)
        }
    }
}