package com.vironit.correctapp.mvp.model.repository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.vironit.correctapp.mvp.model.repository.db.entity.EmployeeDB;

import io.reactivex.Single;

@Dao
public interface EmployeeDAO {

    @Query("SELECT * FROM " + EmployeeDB.EMPLOYEE_TABLE_NAME)
    Single<EmployeeDB> getAllEmployies();
}
