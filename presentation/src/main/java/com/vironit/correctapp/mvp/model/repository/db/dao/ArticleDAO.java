package com.vironit.correctapp.mvp.model.repository.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertNewsDb(ArticleDB... articleDBS);


   // @Query("SELECT * FROM "  + ArticlesDB.ARTICLES_TABLE_NAME + " WHERE " + ArticlesDB.ARTICLES_ID +
   //         " >= :lastNewsId LIMIT :pageSize")

    @Query("SELECT * FROM " + ArticleDB.ARTICLE + " WHERE "+ ArticleDB.ARTICLE_ID + " >= :page LIMIT :pageSize")
    Single<List<ArticleDB>> getAllNewsDB(int page, int pageSize);

    @Delete
    int delete(ArticleDB... articleDBS);
}
