package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.mvp.model.manager.implementation.ResourcesManagerImpl;
import com.vironit.correctapp.mvp.model.manager.interfaces.ResourcesManager;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface ManagerModule {
    @Binds
    @Singleton
    ResourcesManager providesResourcesManager(ResourcesManagerImpl resourcesManagerImpl);
}
