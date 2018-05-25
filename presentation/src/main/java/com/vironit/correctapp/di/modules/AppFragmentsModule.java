package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.di.annotations.FragmentScope;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.ChatFragment;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.NewsFragment;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface AppFragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    ProfileFragment profileFragmentInjector ();

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    NewsFragment newsFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    ChatFragment chatFragmentInjector();


}
