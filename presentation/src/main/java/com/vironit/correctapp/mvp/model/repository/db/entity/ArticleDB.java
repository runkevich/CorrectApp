package com.vironit.correctapp.mvp.model.repository.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vironit.correctapp.mvp.model.repository.dto.Source;

import static com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB.ARTICLE;

@Entity(tableName = ARTICLE)
public class ArticleDB {

    public static final String ARTICLE = "ARTICLE";
    public static final String ARTICLE_ID = "ARTICLE_ID";

    @ColumnInfo(name = ARTICLE_ID)
    @Nullable
    private Long articleId;

    @Embedded
    @Nullable
    private Source source;

    @ColumnInfo(name = "AUTHOR")
    @Nullable
    private String author;

    @ColumnInfo(name = "TITLE")
    @Nullable
    private String title;

    @ColumnInfo(name = "DESCRIPTION")
    @Nullable
    private String description;

    @PrimaryKey
    @ColumnInfo(name = "URL")
    @NonNull
    private String url;

    @ColumnInfo(name = "URL_TO_IMAGE")
    @Nullable
    private String urlToImage;

    @ColumnInfo(name = "PUBLISHED_AT")
    @Nullable
    private String publishedAt;

    public ArticleDB(Long articleId,
                     @Nullable Source source,
                     @Nullable String author,
                     @Nullable String title,
                     @Nullable String description,
                     @NonNull String url,
                     @Nullable String urlToImage,
                     @Nullable String publishedAt) {
        this.articleId = articleId;
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public static String getARTICLE() {
        return ARTICLE;
    }

    @Nullable
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(@Nullable Long articleId) {
        this.articleId = articleId;
    }

    @Nullable
    public Source getSource() {
        return source;
    }

    public void setSource(@Nullable Source source) {
        this.source = source;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@Nullable String author) {
        this.author = author;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(@Nullable String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @Nullable
    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(@Nullable String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
