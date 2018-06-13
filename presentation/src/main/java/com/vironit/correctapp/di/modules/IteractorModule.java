package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.mvp.model.interactor.implementation.ImageInteractorImpl;
import com.vironit.correctapp.mvp.model.interactor.implementation.NewsInteractorImpl;
import com.vironit.correctapp.mvp.model.interactor.implementation.OauthInteractorImpl;
import com.vironit.correctapp.mvp.model.interactor.interfaces.ImageInteractor;
import com.vironit.correctapp.mvp.model.interactor.interfaces.NewsInteractor;
import com.vironit.correctapp.mvp.model.interactor.interfaces.OauthInteractor;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface IteractorModule {

    @Binds
    @Singleton
    NewsInteractor provideNewsInteractor(NewsInteractorImpl newsInteractor);

    @Binds
    @Singleton
    ImageInteractor provideImageInteractor(ImageInteractorImpl imageInteractor);

    @Binds
    @Singleton
    OauthInteractor provideOauthInteractor(OauthInteractorImpl oauthInteractor);
}
