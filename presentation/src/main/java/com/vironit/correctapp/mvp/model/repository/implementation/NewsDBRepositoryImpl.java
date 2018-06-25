package com.vironit.correctapp.mvp.model.repository.implementation;

import android.util.Log;

import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.mvp.model.repository.db.CorrectDatabase;
import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;
import com.vironit.correctapp.mvp.model.repository.interfaces.NewsDBRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class NewsDBRepositoryImpl implements NewsDBRepository {

    private final CorrectDatabase mCorrectDatabase;
    private final Scheduler mScheduler;

    @Inject
    public NewsDBRepositoryImpl(CorrectDatabase mCorrectDatabase,
                                @Named(AppConstants.IO_SCHEDULER) Scheduler mScheduler) {
        this.mCorrectDatabase = mCorrectDatabase;
        this.mScheduler = mScheduler;
    }

    @Override
    public Single<List<ArticleDB>> getAllNewsDB() {
        return mCorrectDatabase
                .getNewsDAO()
                .getAllNewsDB()
                .subscribeOn(mScheduler);
    }

    @Override
    public Single<List<Long>> addNewsDB(ArticleDB... articleDB) {
       return Single.fromCallable(() -> mCorrectDatabase.getNewsDAO()
               .insertNewsDb(articleDB))
                .subscribeOn(mScheduler)
                .doOnSuccess(list -> Log.i("DB", "success"))
                .doOnError(t -> Log.i("DB", t.getMessage()));
    }
}
