package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.mvp.model.interactor.implementation.NewsInteractorImpl;
import com.vironit.correctapp.mvp.model.interactor.interfaces.NewsInteractor;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface IteractorModule {

    @Binds
    @Singleton
    NewsInteractor provideNewsInteractor(NewsInteractorImpl newsInteractor);

}
