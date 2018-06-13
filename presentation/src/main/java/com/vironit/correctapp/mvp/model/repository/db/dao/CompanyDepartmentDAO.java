package com.vironit.correctapp.mvp.model.repository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.vironit.correctapp.mvp.model.repository.db.entity.CompanyAndDepartmentDB;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CompanyDepartmentDAO {

    @Query("SELECT * from COMPANY")
    Single<List<CompanyAndDepartmentDB>> loadCompanyAndDepartmentDB();
}
