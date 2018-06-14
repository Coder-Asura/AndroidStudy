package com.lxd.common_app.network;

import com.lxd.common_app.model.MovieEntity;

import io.reactivex.Observable;
import retrofit2.http.HTTP;
import retrofit2.http.Query;

/**
 * Created by Liuxd on 2016/9/23 11:27.
 */

public interface ApiService {
    @HTTP(path = "https://api.douban.com/v2/movie/top250", method = "GET")
    Observable<MovieEntity> getMovie(@Query("start") int start, @Query("count") int count);

}
