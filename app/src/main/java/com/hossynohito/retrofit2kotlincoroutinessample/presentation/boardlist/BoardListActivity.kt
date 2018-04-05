package com.hossynohito.retrofit2kotlincoroutinessample.presentation.boardlist

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.widget.Toast
import com.hossynohito.retrofit2kotlincoroutinessample.R
import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Board
import com.hossynohito.retrofit2kotlincoroutinessample.presentation.boarddetail.BoardDetailActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_board_list.*
import javax.inject.Inject

class BoardListActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var presenter: BoardListPresenter

    private val adapter = BoardListAdapter(object : BoardListAdapter.Callback {
        override fun onBoardClick(board: Board) {
            presenter.onBoardClick(board)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    fun addBoards(boards: List<Board>) {
        adapter.addAll(boards)
    }

    fun navigateToBoardDetail(board: Board) {
        BoardDetailActivity.createIntent(this, board)
                .let(this::startActivity)
    }

    fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
