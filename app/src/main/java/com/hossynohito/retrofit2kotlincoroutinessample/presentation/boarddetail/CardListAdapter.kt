package com.hossynohito.retrofit2kotlincoroutinessample.presentation.boarddetail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hossynohito.retrofit2kotlincoroutinessample.R
import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Card
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_card.*

class CardListAdapter(
        private val callback: Callback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Card>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            CardViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val card = items[position]
        (holder as CardViewHolder).bind(card) { callback.onCardLongClick(it) }
    }

    fun clear() {
        items.clear()
    }

    fun addAll(cards: List<Card>) {
        items.addAll(cards)
        notifyDataSetChanged()
    }

    fun add(card: Card) {
        items.add(card)
        notifyItemInserted(items.lastIndex)
    }

    fun remove(cardId: String) {
        val position = items.indexOfFirst { it.id == cardId }
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    private class CardViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
    ), LayoutContainer {

        override val containerView: View? get() = itemView

        fun bind(card: Card, onClick: (Card) -> Unit) {
            itemView.setOnLongClickListener {
                onClick(card)
                return@setOnLongClickListener true
            }
            nameText.text = card.name
            card.labels.forEach {
            }
        }
    }

    interface Callback {
        fun onCardLongClick(card: Card)
    }
}