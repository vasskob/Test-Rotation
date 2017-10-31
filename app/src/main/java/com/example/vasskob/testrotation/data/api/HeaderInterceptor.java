package com.example.vasskob.testrotation.data.api;

import android.support.annotation.NonNull;

import com.example.vasskob.testrotation.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        String token = BuildConfig.API_KEY;
        Request newRequest = request.newBuilder()
                .addHeader("Authorization", "Token " + token)
                .build();
        return chain.proceed(newRequest);
    }
}
