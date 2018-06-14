package com.lxd.common_app.ui.loadmovie;

import com.lxd.common_app.model.MovieEntity;
import com.lxd.common_app.ui.IBasePresenter;
import com.lxd.common_app.ui.IBaseView;

/**
 * Created by Liuxd on 2016/10/28 14:23.
 * <p>
 * 用于管理loadMovie功能模块的View与Presenter
 */

public class MovieContract {
    /**
     * loadMovie模块的View,提供对外的UI更新接口
     */
    interface View extends IBaseView<Presenter> {
        void reFresh(MovieEntity movieEntity);

        void loadMore(MovieEntity movieEntity);

        void loadCompleted();
    }

    /**
     * loadMovie模块的Presenter,负责获取数据并处理,更新UI
     */
    interface Presenter extends IBasePresenter {
        void reFresh();

        void loadMore(int start);
    }
}
