package com.liuxd.baseapplibrary.utils;

import com.liuxd.baseapplibrary.R;

/**
 * Created by Liuxd on 2016/11/9 10:07.
 * 图片加载工具类（支持多种加载框架）
 */

public class ImageLoadUtil {
    private int errorResId = R.mipmap.ic_launcher;
    private int placeholderResId = R.mipmap.ic_launcher;
    private int fallbackResId = R.mipmap.ic_launcher;

    private ImageLoadUtil() {
    }

    private static class ImageLoadHolder {
        private static final ImageLoadUtil INSTANCE = new ImageLoadUtil();
    }

    public static ImageLoadUtil getInstance() {
        return ImageLoadHolder.INSTANCE;
    }

//    public void load(Context context, String url, ImageView imageView) {
//        Glide.with(context)
//                .load(url)
//                .centerCrop()
//                .error(errorResId)
//                .placeholder(placeholderResId)
//                .fallback(fallbackResId)
//                .crossFade()
//                .into(imageView);
//    }
//
//    public void load(Context context, File file, ImageView imageView) {
//        Glide.with(context)
//                .load(file)
//                .centerCrop()
//                .error(errorResId)
//                .placeholder(placeholderResId)
//                .fallback(fallbackResId)
//                .crossFade()
//                .into(imageView);
//    }
//
//    public void load(Context context, int resId, ImageView imageView) {
//        Glide.with(context)
//                .load(resId)
//                .centerCrop()
//                .error(errorResId)
//                .placeholder(placeholderResId)
//                .fallback(fallbackResId)
//                .crossFade()
//                .into(imageView);
//    }
//
//    public void load(Context context, Uri uri, ImageView imageView) {
//        Glide.with(context)
//                .load(uri)
//                .centerCrop()
//                .error(errorResId)
//                .placeholder(placeholderResId)
//                .fallback(fallbackResId)
//                .crossFade()
//                .into(imageView);
//    }
//
//    public void load(Context context, String url, ImageView imageView, int width, int height) {
//        Glide.with(context)
//                .load(url)
//                .centerCrop()
//                .override(width, height)
//                .error(errorResId)
//                .placeholder(placeholderResId)
//                .fallback(fallbackResId)
//                .crossFade()
//                .into(imageView);
//    }
//
//    public void load(Context context, File file, ImageView imageView, int width, int height) {
//        Glide.with(context)
//                .load(file)
//                .centerCrop()
//                .override(width, height)
//                .error(errorResId)
//                .placeholder(placeholderResId)
//                .fallback(fallbackResId)
//                .crossFade()
//                .into(imageView);
//    }
//
//    public void load(Context context, int resId, ImageView imageView, int width, int height) {
//        Glide.with(context)
//                .load(resId)
//                .centerCrop()
//                .override(width, height)
//                .error(errorResId)
//                .placeholder(placeholderResId)
//                .fallback(fallbackResId)
//                .crossFade()
//                .into(imageView);
//    }
//
//    public void load(Context context, Uri uri, ImageView imageView, int width, int height) {
//        Glide.with(context)
//                .load(uri)
//                .centerCrop()
//                .override(width, height)
//                .error(errorResId)
//                .placeholder(placeholderResId)
//                .fallback(fallbackResId)
//                .crossFade()
//                .into(imageView);
//    }
}
