package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.mvp.model.repository.implementation.NewsRepositoryImpl;
import com.vironit.correctapp.mvp.model.repository.interfaces.NewsRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface RepozitoryModule {

    @Binds
    @Singleton
    NewsRepository providesNewsRepository(NewsRepositoryImpl newsRepositoryImpl);
}
