package com.vironit.correctapp.mvp.model.repository.db.converter;

import android.support.annotation.Nullable;

import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;
import com.vironit.correctapp.mvp.model.repository.dto.Article;

public class ArticleToArticleDBConverter {


    public static ArticleDB articlesToArticlesDB(@Nullable Article article) {
        return new ArticleDB(null, article.getSource(),
                article.getAuthor(),
                article.getTitle(),
                article.getDescription(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getPublishedAt());
    }

    public static Article articlesDBToArticl(@Nullable ArticleDB articleDB) {
        return new Article(articleDB.getSource(),
                articleDB.getAuthor(),
                articleDB.getTitle(),
                articleDB.getDescription(),
                articleDB.getUrl(),
                articleDB.getUrlToImage(),
                articleDB.getPublishedAt());
    }
}
