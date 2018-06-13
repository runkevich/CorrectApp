package com.vironit.correctapp.mvp.model.repository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.vironit.correctapp.mvp.model.repository.db.entity.DepatmentDB;

import io.reactivex.Single;

@Dao
public interface DepartmentDAO {

    @Query("SELECT * FROM " + DepatmentDB.DEPARTMENT)
    Single<DepatmentDB> getAllDepatments();
}
