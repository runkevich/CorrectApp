package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.di.annotations.ActivityScope;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.ChatsMainActivity;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.HomeActivity;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.LoginActivity;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.LoginRegistrationActivity;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.TestActivity;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.UserAddToChatsMainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface AppActivitiesModule {


    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    TestActivity testActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    LoginActivity loginActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    HomeActivity homeActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    LoginRegistrationActivity loginRegistrationActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    ChatsMainActivity chatsMainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    UserAddToChatsMainActivity userAddToChatsMainActivity();
}
