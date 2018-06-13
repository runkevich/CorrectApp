package com.vironit.correctapp.mvp.model.repository.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vironit.correctapp.mvp.model.repository.db.entity.EmployeeDB;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface EmployeeDAO {

    @Query("SELECT * FROM " + EmployeeDB.EMPLOYEE)
    Single<EmployeeDB> getAllEmployies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EmployeeDB employeeDB);

    @Query("SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = :id LIMIT 1")
    Single<List<EmployeeDB>> getById(long id);

//    @Query("SELECT * FROM EMPLOYEE WHERE EMPLOYEE_LAST_NAME = :name")
//    Single<List<EmployeeDB>> getByLastName(String name);

   // @Query("SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID LIKE :id_employee AND DEPARTMENT_ID LIKE :id_department")
   // Single<List<EmployeeDB>> getEmployeeOnBackSelect(String id_employee, String id_department);
}
