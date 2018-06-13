package com.vironit.correctapp.mvp.model.repository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.vironit.correctapp.mvp.model.repository.db.entity.CompanyAndDepartmentAndEmployeeDB;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CompanyAndDepartmentAndEmployeeDAO {

    @Query("SELECT * from COMPANY")
    Single<List<CompanyAndDepartmentAndEmployeeDB>> loadCompanyAndDepartmentAndEmployeeDAO();
}
