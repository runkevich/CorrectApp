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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ARTICLE_ID)
    @Nullable
    private Long articleId;

    @Embedded
    @NonNull
    private Source source;

    @ColumnInfo(name = "AUTHOR")
    @NonNull
    private String author;

    @ColumnInfo(name = "TITLE")
    @NonNull
    private String title;

    @ColumnInfo(name = "DESCRIPTION")
    @NonNull
    private String description;

    @ColumnInfo(name = "URL")
    @NonNull
    private String url;

    @ColumnInfo(name = "URL_TO_IMAGE")
    @NonNull
    private String urlToImage;

    @ColumnInfo(name = "PUBLISHED_AT")
    @NonNull
    private String publishedAt;

    public ArticleDB(Long articleId,
                     @NonNull Source source,
                     @NonNull String author,
                     @NonNull String title,
                     @NonNull String description,
                     @NonNull String url,
                     @NonNull String urlToImage,
                     @NonNull String publishedAt) {
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
    public static String getArticleId() {
        return ARTICLE_ID;
    }

    public void setArticleId(@Nullable Long articleId) {
        this.articleId = articleId;
    }

    @NonNull
    public Source getSource() {
        return source;
    }

    public void setSource(@NonNull Source source) {
        this.source = source;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NonNull String author) {
        this.author = author;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @NonNull
    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(@NonNull String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @NonNull
    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(@NonNull String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
