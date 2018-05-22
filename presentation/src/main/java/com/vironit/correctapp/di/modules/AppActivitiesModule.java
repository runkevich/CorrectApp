package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.di.annotations.ActivityScope;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.TestActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface AppActivitiesModule {


    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    TestActivity testActivityInjector();


}
