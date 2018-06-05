package com.vironit.correctapp.mvp.model.manager.implementation.network;

import com.vironit.correctapp.BuildConfig;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Inject
    public HeaderInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder mBuilder = originalRequest.newBuilder();

        mBuilder.header("Authorization","Bearer "+ BuildConfig.NEWS_API_KEY);
        return chain.proceed(mBuilder.build());
    }
}
