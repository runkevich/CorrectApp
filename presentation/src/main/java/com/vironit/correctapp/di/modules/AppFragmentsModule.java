package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.di.annotations.FragmentScope;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.ChatsFragment;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.ChatsMessageFragment;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.MapFragment;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.NewsFragment;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.ProfileFragment;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.UserAddToChatsMainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface AppFragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    ProfileFragment profileFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    NewsFragment newsFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    MapFragment mapFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    ChatsFragment chatFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    UserAddToChatsMainFragment userAddToChatsMainFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    ChatsMessageFragment chatsMainFragment();
}
