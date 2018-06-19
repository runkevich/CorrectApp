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

   //@Query("SELECT COMPANY.*, DEPARTMENT.*, EMPLOYEE.* FROM EMPLOYEE INNER JOIN DEPARTMENT ON EMPLOYEE.DEPARTMENT_ID = DEPARTMENT.DEPARTMENT_ID INNER JOIN COMPANY ON COMPANY.COMPANY_ID = DEPARTMENT.COMPANY_ID WHERE employee_id = :employee_id LIMIT 1")
    //Single<CompanyAndDepartmentAndEmployeeFromReverse> getCompanyWithDepartmentsAndEmployeesFromReverse(Long employee_id);
}
