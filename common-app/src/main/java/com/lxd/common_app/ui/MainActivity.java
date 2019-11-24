package com.lxd.common_app.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.RecyclerView;
import androidx.appcompat.widget.StaggeredGridLayoutManager;
import android.widget.Button;
import android.widget.TextView;

import com.lxd.common_app.R;
import com.lxd.common_app.model.MovieEntity;
import com.lxd.common_app.network.HttpUtils;
import com.lxd.common_app.ui.adapter.MovieAdapter;
import com.lxd.common_app.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.rv_movie)
    RecyclerView mRvMovie;

    private MovieAdapter mMovieAdapter;
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMovieAdapter = new MovieAdapter(this);
        mRvMovie.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRvMovie.setAdapter(mMovieAdapter);
    }

    @OnClick(R.id.button)
    public void onClick() {
//        Call<MovieEntity> call = HttpUtils.getInstance().getApiService().getMovie(0, 1);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                mTv.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                mTv.setText(t.getMessage());
//            }
//        });
//        HttpUtils.getInstance().getApiService().getVersion("CFVUAKQPZJIACHLN")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mTv.setText(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        LogUtil.d("接收的："+s);
//                        mTv.setText(s);
//                    }
//                });

        HttpUtils.getInstance().getApiService().getMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.d("onStart");
                        time = System.currentTimeMillis();
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        LogUtil.d("onNext:" + movieEntity.toString());
                        mMovieAdapter.setMovieEntity(movieEntity, false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("onError:" + e.getMessage());
                        mTv.setText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d("onCompleted");
                        time = System.currentTimeMillis() - time;
                        mTv.setText("完成,耗时 " + time + " ms");
                    }
                });
    }
}
