package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.mvp.model.repository.ApiInterface;
import com.vironit.correctapp.mvp.model.repository.ApiInterfaceImage;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    ApiInterface provideInterface(@Named(AppConstants.NEWS) Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    ApiInterfaceImage provideInterfaceImage(@Named(AppConstants.IMAGES) Retrofit retrofit) {
        return retrofit.create(ApiInterfaceImage.class);
    }
}
