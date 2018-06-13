package com.asura.jsoup_study.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Converter;

/**
 * @author Created by Asura on 2018/2/7 10:34.
 */
public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        String response = responseBody.string();
//        HttpStatus httpStatus = gson.fromJson(response, HttpStatus.class);
//        if (httpStatus.isCodeInvalid()) {
//            value.close();
//            if (httpStatus.getCode() == 30015 || httpStatus.getCode() == 30016) {
//                throw new TokenOutDateException();
//            } else {
//                throw new ApiException(httpStatus.getCode());
//            }
//        }
        //拦截网页 html 标签数据
        if (response.contains("<!DOCTYPE html>")) {
            responseBody.close();
            return (T) response;
        }

        MediaType contentType = responseBody.contentType();
        Charset charset = contentType != null ? contentType.charset(Util.UTF_8) : Util.UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);

        try {
            return adapter.read(jsonReader);
        } finally {
            responseBody.close();
        }
    }
}