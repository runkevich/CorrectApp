package com.vironit.correctapp.mvp.model.interactor.implementation;

import com.vironit.correctapp.mvp.model.interactor.interfaces.NewsInteractor;
import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;
import com.vironit.correctapp.mvp.model.repository.dto.Data;
import com.vironit.correctapp.mvp.model.repository.interfaces.NewsDBRepository;
import com.vironit.correctapp.mvp.model.repository.interfaces.NewsRepository;
import com.vironit.correctapp.utils.ErrorHandlerUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class NewsInteractorImpl implements NewsInteractor {

    private final NewsRepository mNewsRepository;
    private final NewsDBRepository mNewsDBRepository;

    @Inject
    public NewsInteractorImpl(NewsRepository mNewsRepository,
                              NewsDBRepository mNewsDBRepository) {
        this.mNewsRepository = mNewsRepository;
        this.mNewsDBRepository = mNewsDBRepository;
    }

    @Override
    public Single<Data> getNews(int page, int pageSize) {
        return mNewsRepository.getNews("ru",page,pageSize)
                .onErrorResumeNext(ErrorHandlerUtil::defaultHandle);
    }
//
//    @Override
//    public Single<List<ArticleDB>> getAllNewsDB() {
//        return mNewsDBRepository.getAllNewsDB();
//    }

    @Override
    public Single<List<Long>> addNewsDB(ArticleDB... articleDB) {
      return mNewsDBRepository.addNewsDB(articleDB);
        //articlesDB.length != 0 ? mNewsDBRepository.addNewsToDB(articlesDB) : Single.never();
    }
}
