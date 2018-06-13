package com.vironit.correctapp.mvp.model.interactor.implementation;

import com.vironit.correctapp.mvp.model.interactor.interfaces.NewsInteractor;
import com.vironit.correctapp.mvp.model.repository.dto.Data;
import com.vironit.correctapp.mvp.model.repository.interfaces.NewsRepository;
import com.vironit.correctapp.utils.ErrorHandlerUtil;

import javax.inject.Inject;

import io.reactivex.Single;

public class NewsInteractorImpl implements NewsInteractor {

    private final NewsRepository mNewsRepository;

    @Inject
    public NewsInteractorImpl(NewsRepository mNewsRepository) {
        this.mNewsRepository = mNewsRepository;
    }

    @Override
    public Single<Data> getNews(int page, int pageSize) {
        return mNewsRepository.getNews("ru",page,pageSize)
                .onErrorResumeNext(ErrorHandlerUtil::defaultHandle);
    }
}
