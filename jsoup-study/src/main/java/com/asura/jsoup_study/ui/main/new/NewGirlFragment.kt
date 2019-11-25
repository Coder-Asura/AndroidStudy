package com.asura.jsoup_study.ui.main.new

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.asura.jsoup_study.R
import com.asura.jsoup_study.base.BaseRxLifecycleFragment
import com.asura.jsoup_study.bean.NewGirl
import kotlinx.android.synthetic.main.fragment_new_girl.*

/**
 * @author Created by Asura on 2018/6/8 11:26.
 */
class NewGirlFragment : BaseRxLifecycleFragment<INewGirlView, NewGirlPresenter>(), INewGirlView {
    //页码从1开始
    private var currentPage = 1
    private val PAGE_SIZE = 18
    lateinit var data: ArrayList<NewGirl>
    lateinit var adapter: NewGirlAdapter

    override fun getLayoutId(): Int = R.layout.fragment_new_girl

    override fun initViews(rootView: View) {
        data = ArrayList()
        (swipe_new_girl as SwipeRefreshLayout).setOnRefreshListener {
            currentPage++
            loadData(false, currentPage, PAGE_SIZE)
            showLoadingView()
        }
        (rv_new_girl as RecyclerView).layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        adapter = NewGirlAdapter(getAc(), data, object : NewGirlAdapter.Callback {
            override fun onItemClick(position: Int) {
                toastShort("点击了$position 套图下次做")
            }
        })
        (rv_new_girl as RecyclerView).adapter = adapter
    }

    override fun initDatas() {
        super.initDatas()
        showLoadingView()
        loadData(false, currentPage, PAGE_SIZE)
    }

    override fun showLoadingView() {
        (swipe_new_girl as SwipeRefreshLayout).isRefreshing = false
    }

    override fun showEmptyView() {

    }

    override fun showErrorView() {
    }

    override fun showContentView() {
        (swipe_new_girl as SwipeRefreshLayout).isRefreshing = false
    }

    override fun showData(data: List<NewGirl>) {
        this.data.clear()
        this.data.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun loadData(loadMore: Boolean, page: Int, size: Int) {
        getPresenter().loadList(loadMore, page, size)
    }

    override fun createPresenter(): NewGirlPresenter {
        return NewGirlPresenter(this)
    }

    fun quickToTop() {
        rv_new_girl.post {
            run {
//                rv_new_girl.scrollToPosition(0)
                (rv_new_girl as RecyclerView).smoothScrollToPosition(0)
            }
        }
    }
}