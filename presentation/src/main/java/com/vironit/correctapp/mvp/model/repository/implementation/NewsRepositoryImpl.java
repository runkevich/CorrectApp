package com.vironit.correctapp.mvp.model.repository.implementation;

import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.mvp.model.repository.ApiInterface;
import com.vironit.correctapp.mvp.model.repository.dto.Data;
import com.vironit.correctapp.mvp.model.repository.interfaces.NewsRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class NewsRepositoryImpl implements NewsRepository {

    private final ApiInterface mApiInterface;
    private final Scheduler mIOScheduler;

    @Inject
    public NewsRepositoryImpl(ApiInterface mApiInterface,
                              @Named(AppConstants.IO_SCHEDULER) Scheduler mIOScheduler) {
        this.mApiInterface = mApiInterface;
        this.mIOScheduler = mIOScheduler;
    }

    @Override
    public Single<Data> getNews(String countryCode, int page, int pageSize) {
        return mApiInterface.getNews(countryCode)
                .subscribeOn(mIOScheduler);
    }
}
