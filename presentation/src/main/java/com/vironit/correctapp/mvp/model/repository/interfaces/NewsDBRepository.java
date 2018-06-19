package com.vironit.correctapp.mvp.model.repository.interfaces;

import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;

import java.util.List;

import io.reactivex.Single;

public interface NewsDBRepository {

    Single<List<ArticleDB>> getAllNewsDB();
    void addNewsDB(ArticleDB... articleDB);
}
