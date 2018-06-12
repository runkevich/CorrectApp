package com.vironit.correctapp.mvp.model.repository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Transaction;

import com.vironit.correctapp.mvp.model.repository.db.entity.CompanyDB;
import com.vironit.correctapp.mvp.model.repository.db.entity.DepatmentDB;

@Dao
public abstract class CompanyDepartmentDAO {

    @Insert
    public abstract void insertCompany(CompanyDB companyDB);

    @Insert
    public abstract void insertDepartment(DepatmentDB depatmentDB);

    @Transaction
    public void insetCompanyAndDepartment(CompanyDB companyDB, DepatmentDB depatmentDB) {
        insertCompany(companyDB);
        insertDepartment(depatmentDB);
    }
}
