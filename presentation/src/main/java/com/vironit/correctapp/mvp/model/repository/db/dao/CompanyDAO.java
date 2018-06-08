package com.vironit.correctapp.mvp.model.repository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.vironit.correctapp.mvp.model.repository.db.entity.CompanyDB;

import io.reactivex.Single;

@Dao
public interface CompanyDAO {

    @Query("SELECT * FROM " + CompanyDB.COMPANY_TABLE_NAME)
    Single<CompanyDB> getAllCompanies();
}
