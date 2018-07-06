package com.vironit.correctapp.di.components;


import com.vironit.correctapp.App;
import com.vironit.correctapp.di.modules.ApiModule;
import com.vironit.correctapp.di.modules.AppActivitiesModule;
import com.vironit.correctapp.di.modules.AppFragmentsModule;
import com.vironit.correctapp.di.modules.ApplicationModule;
import com.vironit.correctapp.di.modules.DataBaseModule;
import com.vironit.correctapp.di.modules.FragmentModule;
import com.vironit.correctapp.di.modules.IteractorModule;
import com.vironit.correctapp.di.modules.ManagerModule;
import com.vironit.correctapp.di.modules.ParseModule;
import com.vironit.correctapp.di.modules.RepozitoryModule;
import com.vironit.correctapp.di.modules.RetrofitModule;
import com.vironit.correctapp.di.modules.SchedulersModule;
import com.vironit.correctapp.di.modules.SocialNetworkModule;
import com.vironit.correctapp.mvp.presentation.presenter.ChatPresenter;
import com.vironit.correctapp.mvp.presentation.presenter.HomePresenter;
import com.vironit.correctapp.mvp.presentation.presenter.LoginPresenter;
import com.vironit.correctapp.mvp.presentation.presenter.LoginRegistrationPresenter;
import com.vironit.correctapp.mvp.presentation.presenter.NewsPresenter;
import com.vironit.correctapp.mvp.presentation.presenter.ProfilePresenter;
import com.vironit.correctapp.mvp.presentation.presenter.TestPresenter;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


//предоставляет - собирает
@Singleton
@Component(modules = {SchedulersModule.class, RepozitoryModule.class, ManagerModule.class, AppActivitiesModule.class,
        ApplicationModule.class, IteractorModule.class, SocialNetworkModule.class, FragmentModule.class, AppFragmentsModule.class,
        RetrofitModule.class, ApiModule.class, ParseModule.class, DataBaseModule.class})
public interface AppComponent {

    @Component.Builder interface Builder {
        @BindsInstance
        Builder appContext(App app);

        AppComponent build();
    }

    void inject(App app);

    void inject(TestPresenter testPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(ChatPresenter chatPresenter);

    void inject(ProfilePresenter profilePresenter);

    void inject(NewsPresenter newsPresenter);

    void inject(HomePresenter homePresenter);

    void inject(LoginRegistrationPresenter loginRegistrationPresenter);
}
