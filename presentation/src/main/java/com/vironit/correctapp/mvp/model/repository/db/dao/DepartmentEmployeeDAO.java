package com.vironit.correctapp.mvp.model.repository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Transaction;

import com.vironit.correctapp.mvp.model.repository.db.entity.DepatmentDB;
import com.vironit.correctapp.mvp.model.repository.db.entity.EmployeeDB;

@Dao
public abstract class DepartmentEmployeeDAO {

    @Insert
    public abstract void insertDepartment(DepatmentDB depatmentDB);

    @Insert
    public abstract void insertEmployee(EmployeeDB employeeDB);

    @Transaction
    public void insetDepartmentAndEmployee(DepatmentDB depatmentDB, EmployeeDB employeeDB) {
        insertDepartment(depatmentDB);
        insertEmployee(employeeDB);
    }
}
