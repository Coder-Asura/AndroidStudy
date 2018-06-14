package com.lxd.common_app.ui.loadmovie;

import com.lxd.common_app.model.MovieEntity;
import com.lxd.common_app.network.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Liuxd on 2016/10/28 14:29.
 */

public class MoviePresenter implements MovieContract.Presenter {
    MovieContract.View mView;

    public MoviePresenter(MovieContract.View view) {
        mView = view;
//        mView.setPresenter(this);
    }

    @Override
    public void reFresh() {
        HttpUtils.getInstance().getApiService().getMovie(0, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieEntity>() {
                    @Override
                    public void accept(MovieEntity movieEntity) {
                        mView.reFresh(movieEntity);
                    }
                });
    }

    @Override
    public void loadMore(int start) {
        HttpUtils.getInstance().getApiService().getMovie(start, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieEntity>() {
                    @Override
                    public void accept(MovieEntity movieEntity) {
                        mView.loadMore(movieEntity);
                        mView.loadCompleted();//改变加载状态
                    }
                });
    }

    @Override
    public void onStart() {

    }
}
