package com.vironit.correctapp.mvp.model.interactor.interfaces;

import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;
import com.vironit.correctapp.mvp.model.repository.dto.Data;

import java.util.List;

import io.reactivex.Single;

public interface NewsInteractor {

    Single<Data> getNews(int page, int pageSize);

    Single<List<Long>> addNewsToDB(ArticleDB... articleDB);

    Single<List<ArticleDB>> getNewsFromDB(int page, int pageSize);
    Single<Integer> deleteNews(ArticleDB... articleDB);
}
