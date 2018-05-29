package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.mvp.model.repository.ApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule  {

    @Provides
    @Singleton
    ApiInterface provideInterface(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }
}
