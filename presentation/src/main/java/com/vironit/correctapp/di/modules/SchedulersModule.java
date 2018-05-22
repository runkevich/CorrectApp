package com.vironit.correctapp.di.modules;

import com.vironit.correctapp.constans.AppConstans;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class SchedulersModule {

    @Provides
    @Singleton
    @Named(AppConstans.COMPUTATION_SCHEDULER)
    Scheduler provideCompitationScheduler(){
        return Schedulers.computation();
   }

   @Provides
   @Singleton
   @Named(AppConstans.IO_SCHEDULER)
    Scheduler provideIOScheduler(){
       return Schedulers.io();
    }


    @Provides
    @Singleton
    @Named(AppConstans.UI_SCHEDULER)
    Scheduler provideUIScheduler(){
        return AndroidSchedulers.mainThread();
    }
}
