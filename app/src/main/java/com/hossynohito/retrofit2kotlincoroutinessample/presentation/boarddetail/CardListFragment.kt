package com.hossynohito.retrofit2kotlincoroutinessample.presentation.boarddetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hossynohito.retrofit2kotlincoroutinessample.R
import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Card
import dagger.Provides
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_card_list.*
import javax.inject.Inject

class CardListFragment : DaggerFragment() {

    @Inject
    lateinit var presenter: CardListFragmentPresenter

    private val pipeLineId: String by lazy { arguments!!.getString(KEY_PIPE_LINE_ID) }

    private val adapter = CardListAdapter(object : CardListAdapter.Callback {
        override fun onCardLongClick(card: Card) { presenter.onCardLongClick(card) }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_card_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter

        addButton.setOnClickListener { addEditText.text.toString().let(presenter::onAddButtonClick)  }

        presenter.onViewCreated()
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        super.onDestroyView()
    }

    fun addCards(cards: List<Card>) {
        adapter.addAll(cards)
    }

    fun addCard(card: Card) {
        adapter.add(card)
    }

    fun removeCard(cardId: String) {
        adapter.remove(cardId)
    }

    fun showCardAddToast() {
        Toast.makeText(activity!!, "Card added!", Toast.LENGTH_SHORT).show()
    }

    fun showCardDeleteToast() {
        Toast.makeText(activity!!, "Card deleted!", Toast.LENGTH_SHORT).show()
    }

    fun showErrorToast(message: String) {
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }

    companion object {

        private const val KEY_PIPE_LINE_ID = "KEY_PIPE_LINE_ID"

        fun newInstance(pipeLineId: String) = CardListFragment()
                .apply {
                    arguments = Bundle().apply { putString(KEY_PIPE_LINE_ID, pipeLineId) }
                }
    }

    @dagger.Module
    class Module {

        @Provides
        fun providePipelineId(fragment: CardListFragment) = fragment.pipeLineId
    }
}