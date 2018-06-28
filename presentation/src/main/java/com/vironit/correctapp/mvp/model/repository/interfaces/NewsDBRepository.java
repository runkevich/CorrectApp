package com.vironit.correctapp.mvp.model.repository.interfaces;

import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;

import java.util.List;

import io.reactivex.Single;

public interface NewsDBRepository {

    Single<List<ArticleDB>> getAllNewsFromDB(int page, int pageSize);
    Single<List<Long>> addNewsToDB(ArticleDB... articleDB);

    Single<Integer> deleteNews(ArticleDB... articleDB);
}
