package com.vironit.correctapp.mvp.model.manager.implementation.network;

import com.vironit.correctapp.mvp.model.manager.implementation.TockenManagerImpl;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptorLivia implements Interceptor {

    TockenManagerImpl mTockenManager;

    @Inject
    public HeaderInterceptorLivia(TockenManagerImpl mTockenManager) {
        this.mTockenManager = mTockenManager;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder mBuilder = originalRequest.newBuilder();
        if (mTockenManager.isTockenValid()){
            mBuilder.header("LIVIAPP-TOCKEN",mTockenManager.getTocken().getAccess());
            return chain.proceed(mBuilder.build());
        }
       return null;
    }
}
