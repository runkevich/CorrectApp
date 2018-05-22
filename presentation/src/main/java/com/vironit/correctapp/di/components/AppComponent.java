package com.vironit.correctapp.di.components;


import com.vironit.correctapp.App;
import com.vironit.correctapp.di.modules.AppActivitiesModule;
import com.vironit.correctapp.di.modules.ApplicationModule;
import com.vironit.correctapp.di.modules.IteractorModule;
import com.vironit.correctapp.di.modules.ManagerModule;
import com.vironit.correctapp.di.modules.RepozitoryModule;
import com.vironit.correctapp.di.modules.SchedulersModule;
import com.vironit.correctapp.mvp.presentation.presenter.TestPresenter;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


//предоставляет - собирает
@Singleton
@Component(modules = {SchedulersModule.class, RepozitoryModule.class, ManagerModule.class, AppActivitiesModule.class,
        ApplicationModule.class, IteractorModule.class})
public interface AppComponent {


    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder appContext(App app);

        AppComponent build();

    }

    void inject(App app);
    void inject(TestPresenter testPresenter);
}
