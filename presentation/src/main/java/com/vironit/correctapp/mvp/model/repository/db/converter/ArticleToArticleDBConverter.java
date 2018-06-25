package com.vironit.correctapp.mvp.model.repository.db.converter;

import android.support.annotation.Nullable;

import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;
import com.vironit.correctapp.mvp.model.repository.dto.Article;

public class ArticleToArticleDBConverter {



//    @TypeConverter
//    public ArticleDB[] articlesToArticlesDB(Article article){
//        List<ArticleDB> articlesDB = new ArrayList<>();
//        for (Article a: article) {
//            //articlesDB.add(ArticleToArticleDBConverter.ArticleToArticlesDB(article));
//        }
//        ArticleDB[] resultList = new ArticleDB[];
//        resultList = articlesDB.toArray(resultList);
//        return articlesDB;
//    }

    public static ArticleDB articlesToArticlesDB(@Nullable Article article){
        return new ArticleDB(null,article.getSource(),
                article.getAuthor(),
                article.getTitle(),
                article.getDescription(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getPublishedAt());
    }

    public static Article articlesDBToArticl(@Nullable ArticleDB articleDB){
        return new Article(articleDB.getSource(),
                articleDB.getAuthor(),
                articleDB.getTitle(),
                articleDB.getDescription(),
                articleDB.getUrl(),
                articleDB.getUrlToImage(),
                articleDB.getPublishedAt());
    }
}
