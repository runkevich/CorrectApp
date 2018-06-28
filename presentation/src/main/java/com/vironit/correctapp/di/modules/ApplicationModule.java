package com.vironit.correctapp.di.modules;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vironit.correctapp.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Context provideContext(App app) {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(App app) {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }
}
