package com.hossynohito.retrofit2kotlincoroutinessample.presentation.boardlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hossynohito.retrofit2kotlincoroutinessample.R
import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Board
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_board.*


class BoardListAdapter(
        private val listener: Callback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Board>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            BoardViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val board = items[position]
        (holder as BoardViewHolder).bind(board) { listener.onBoardClick(it) }
    }

    fun addAll(boards: List<Board>) {
        items.addAll(boards)
        notifyDataSetChanged()
    }

    private class BoardViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_board, parent, false)
    ), LayoutContainer {

        override val containerView: View? get() = itemView

        fun bind(board: Board, onClick: (Board) -> Unit) {
            itemView.setOnClickListener { onClick(board) }
            nameText.text = board.name
        }
    }

    interface Callback {
        fun onBoardClick(board: Board)
    }
}
