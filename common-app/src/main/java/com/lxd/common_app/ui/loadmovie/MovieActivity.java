package com.lxd.common_app.ui.loadmovie;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.RecyclerView;
import androidx.appcompat.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.lxd.common_app.R;
import com.lxd.common_app.model.MovieEntity;
import com.lxd.common_app.ui.adapter.MovieAdapter;
import com.lxd.common_app.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lxd.common_app.R.id.srl;

/**
 * Created by Liuxd on 2016/10/28 14:27.
 * 电影列表页面
 */

public class MovieActivity extends AppCompatActivity implements MovieContract.View {
    @BindView(R.id.rv_load_movie)
    RecyclerView mRvLoadMovie;
    @BindView(srl)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private MovieContract.Presenter mPresenter = new MoviePresenter(this);

    private MovieAdapter mMovieAdapter;
    private boolean isLoading = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mProgressBar.setVisibility(View.GONE);
        mSrl.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        mSrl.post(new Runnable() {
            @Override
            public void run() {
                mSrl.setRefreshing(true);
            }
        });
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.reFresh();
            }
        });
        mMovieAdapter = new MovieAdapter(this);
        mMovieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                LogUtil.d("点击了" + position);
            }
        });
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRvLoadMovie.setLayoutManager(layoutManager);
        mRvLoadMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastPositions[] = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
                int lastVisiableItemPos = findMax(lastPositions);
                if (lastVisiableItemPos == mMovieAdapter.getItemCount() - 1) {
                    if (!isLoading) {
                        isLoading = true;
                        mProgressBar.setVisibility(View.VISIBLE);
                        mPresenter.loadMore(lastVisiableItemPos + 1);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        mRvLoadMovie.setAdapter(mMovieAdapter);
        mPresenter.reFresh();
    }

    @Override
    public void reFresh(MovieEntity movieEntity) {
        mMovieAdapter.setMovieEntity(movieEntity, false);
        mSrl.setRefreshing(false);
    }

    @Override
    public void loadMore(MovieEntity movieEntity) {
        mMovieAdapter.setMovieEntity(movieEntity, true);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadCompleted() {
        this.isLoading = false;
    }

//    @Override
//    public void setPresenter(MovieContract.Presenter presenter) {
//        this.mPresenter = presenter;
//    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
