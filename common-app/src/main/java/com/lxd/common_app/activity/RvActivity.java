package com.lxd.common_app.activity;

import androidx.core.widget.SwipeRefreshLayout;
import androidx.appcompat.widget.RecyclerView;
import androidx.appcompat.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lxd.common_app.R;
import com.lxd.common_app.base.BaseActivity;
import com.lxd.common_app.base.BaseAdapter;
import com.lxd.common_app.base.BaseViewHolder;
import com.lxd.common_app.model.MovieEntity;
import com.lxd.common_app.network.HttpUtils;
import com.lxd.common_app.utils.LogUtil;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Liuxd on 2016/11/8 15:29.
 */

public class RvActivity extends BaseActivity {

    @BindView(R.id.rv_load_movie)
    RecyclerView mRvLoadMovie;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    public int setLayoutId() {
        return R.layout.activity_movie;
    }

    @Override
    public void init() {
        final BaseAdapter<MovieEntity.Subject> commonAdapter = new BaseAdapter<MovieEntity.Subject>(null, R.layout.item_movie) {
            @Override
            public void convert(BaseViewHolder holder, MovieEntity.Subject data) {
                TextView textView = holder.getView(R.id.movie_name);
                textView.setText(data.getTitle());
                LogUtil.d(data.getTitle());
                holder.setText(R.id.movie_desc, data.getOriginal_title());
                holder.setImageSrc(R.id.movie_pic, data.getImages().getLarge());
            }
        };
        commonAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                LogUtil.d("点击了" + position);
                if (position == 0) {
//                    HttpUtils.getInstance().getApiService().getData("CFVUAKQPZJIACHLN")
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Action1<JsonObject>() {
//                                @Override
//                                public void call(JsonObject s) {
//                                    LogUtil.i(s.toString());
//                                    showToast("数据" + s);
//                                }
//                            });
                } else if (position == 3) {
//                    HttpUtils.getInstance().getApiService().getVersion("CFVUAKQPZJIACHLN")
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Action1<String>() {
//                                @Override
//                                public void call(String s) {
//                                    LogUtil.i(s);
//                                    showToast("版本" + s);
//                                }
//                            });
                }
            }
        });
        mRvLoadMovie.setAdapter(commonAdapter);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRvLoadMovie.setLayoutManager(layoutManager);
        HttpUtils.getInstance().getApiService().getMovie(0, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieEntity>() {
                    @Override
                    public void accept(MovieEntity movieEntity) {
                        commonAdapter.setDatas(movieEntity.getSubjects(), false);
                    }
                });
    }

}
