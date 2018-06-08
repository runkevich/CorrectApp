package com.vironit.correctapp.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ParseModule {

    @Provides
    @Singleton
    Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
                .create();
    }
}
