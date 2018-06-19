package com.vironit.correctapp.mvp.model.repository.implementation;

import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.mvp.model.manager.interfaces.TockenManager;
import com.vironit.correctapp.mvp.model.repository.ApiOauthInterface;
import com.vironit.correctapp.mvp.model.repository.interfaces.OauthRepository;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class OauthRepositoryImpl implements OauthRepository {

    private TockenManager mTockenManager;
    private final AtomicBoolean mIsTockenUpdatingNow = new AtomicBoolean(false);
    private Scheduler mScheduler;
    private ApiOauthInterface mApiOauthInterface;

    @Inject
    public OauthRepositoryImpl(TockenManager mTockenManager,
                               @Named(value = AppConstants.IO_SCHEDULER) Scheduler mScheduler,
                               ApiOauthInterface mApiOauthInterface) {
        this.mTockenManager = mTockenManager;
        this.mScheduler = mScheduler;
        this.mApiOauthInterface = mApiOauthInterface;
    }

    private Single<Boolean> updateTocken() {
        if (mIsTockenUpdatingNow.get()) {
            return Single.timer(100, TimeUnit.MILLISECONDS, mScheduler)
                    .flatMap(aLong -> updateTockenIfNeeded());//retry
        } else {
            return Single.fromCallable(() -> {
                mIsTockenUpdatingNow.set(true);

                return true;
                // todo return mApiAuthInterface;
            })
                    .flatMap(apiAuthInterface -> Single.just(true)
                            //todo get from network
                    )
                    .map(newToken -> true
                            // todo save new updated token to manager
                    )
                    .map(aBoolean -> mIsTockenUpdatingNow.getAndSet(false));
        }
    }

    @Override
    public Single<Boolean> updateTockenIfNeeded() {
        if (mTockenManager.isTockenValid()) {
            return Single.just(true);
        } else return updateTocken();
    }
}
