package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.mvp.model.repository.implementation.ImageRepositoryImpl;
import com.vironit.correctapp.mvp.model.repository.implementation.NewsRepositoryImpl;
import com.vironit.correctapp.mvp.model.repository.implementation.OauthRepositoryImpl;
import com.vironit.correctapp.mvp.model.repository.interfaces.ImageRepository;
import com.vironit.correctapp.mvp.model.repository.interfaces.NewsRepository;
import com.vironit.correctapp.mvp.model.repository.interfaces.OauthRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface RepozitoryModule {

    @Binds
    @Singleton
    NewsRepository providesNewsRepository(NewsRepositoryImpl newsRepositoryImpl);

    @Binds
    @Singleton
    ImageRepository providesImageRepository(ImageRepositoryImpl imageRepository);

    @Binds
    @Singleton
    OauthRepository providesOauthRepository(OauthRepositoryImpl oauthRepository);
}
