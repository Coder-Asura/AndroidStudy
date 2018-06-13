package com.asura.jsoup_study.util;

import android.content.Context;
import android.widget.ImageView;

import com.asura.jsoup_study.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author Created by Liuxd on 2018/2/3 16:54.
 * 图片工具类
 */
public class ImageUtils {
    public static void loadImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.bg_loading)
                .error(R.drawable.bg_loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop();
        Glide.with(context).load(url).apply(options).into(imageView);
    }

//    public static void compressImage(final Context context, List<String> imgPaths,
//                                     BaseProgressObserver<Map<String, RequestBody>> observer) {
//        Observable.just(imgPaths)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .map(new Function<List<String>, List<File>>() {
//                    @Override
//                    public List<File> apply(@NonNull List<String> list) throws Exception {
//                        // 同步方法直接返回压缩后的文件
//                        return Luban.with(context).load(list).get();
//                    }
//                })
//                .map(new Function<List<File>, Map<String, RequestBody>>() {
//                    @Override
//                    public Map<String, RequestBody> apply(List<File> files) throws Exception {
//                        Map<String, RequestBody> params = new LinkedHashMap<>();
//                        for (File file : files) {
//                            ALog.d(file.getPath() + "...路径。。。" + file.getName());
//                            // create RequestBody instance from file
//                            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                            //一定要加("AttachmentKey\"; filename=\"" +，不然失败
////                                    params.put("AttachmentKey\"; filename=\"" + file.getName(), requestFile);
//                            params.put("file[]\"; filename=\"" + file.getName(), requestFile);
//                        }
//                        return params;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
}
