package com.vironit.correctapp.mvp.model.interactor.interfaces;

import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;
import com.vironit.correctapp.mvp.model.repository.dto.Data;

import java.util.List;

import io.reactivex.Single;

public interface NewsInteractor {

    Single<Data> getNews(int page, int pageSize);

    //Single<List<ArticleDB>> getAllNewsDB(ArticleDB... articlesDB);
    Single<List<Long>> addNewsDB(ArticleDB... articleDB);
   //void addNewsDB (List<ArticleDB> articleDBS);
}
