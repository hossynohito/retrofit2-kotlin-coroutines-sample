package com.hossynohito.retrofit2kotlincoroutinessample.presentation.boarddetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.widget.Toast
import com.hossynohito.retrofit2kotlincoroutinessample.R
import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Board
import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Pipeline
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_board_detail.*
import javax.inject.Inject

class BoardDetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var presenter: BoardDetailPresenter

    private val board by lazy { intent.getSerializableExtra(KEY_BOARD) as Board }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    fun setViewPager(pipelines: List<Pipeline>) {
        val pagerAdapter = PipelinePagerAdapter(supportFragmentManager, pipelines)
        viewPager.adapter = pagerAdapter
        viewPager.offscreenPageLimit = pipelines.size
        tabLayout.setupWithViewPager(viewPager)
    }

    fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val KEY_BOARD = "KEY_BOARD"

        fun createIntent(activity: Activity, board: Board) = Intent(activity, BoardDetailActivity::class.java)
                .apply {
                    putExtra(KEY_BOARD, board)
                }
    }

    private class PipelinePagerAdapter(
            fragmentManager: FragmentManager,
            private val pipelines: List<Pipeline>
    ) : FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment = pipelines[position].let { CardListFragment.newInstance(it.id) }

        override fun getCount(): Int = pipelines.size

        override fun getPageTitle(position: Int): CharSequence? = pipelines[position].name
    }

    @dagger.Module
    class Module {

        @Provides
        fun provideBoard(activity: BoardDetailActivity) = activity.board
    }

    @dagger.Module
    interface FragmentModule {

        @ContributesAndroidInjector(modules = [(CardListFragment.Module::class)])
        fun contributeCardListFragment(): CardListFragment
    }
}
