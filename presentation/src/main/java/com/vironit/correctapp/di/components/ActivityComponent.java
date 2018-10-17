package com.vironit.correctapp.di.components;

import com.vironit.correctapp.di.annotations.ActivityScope;
import com.vironit.correctapp.di.modules.ActivityModule;

import dagger.Component;

@ActivityScope @Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

}
