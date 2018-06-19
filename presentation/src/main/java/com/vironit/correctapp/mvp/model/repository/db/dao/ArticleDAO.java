package com.vironit.correctapp.mvp.model.repository.db.dao;


import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;

import java.util.List;

import io.reactivex.Single;

public interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertNewsDB(ArticleDB... articleDBS);

    @Query("SELECT * FROM " + ArticleDB.ARTICLE)
    Single<List<ArticleDB>> getAllNewsDB();
}
