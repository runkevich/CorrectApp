package com.vironit.correctapp.mvp.model.repository.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import static com.vironit.correctapp.mvp.model.repository.db.entity.CompanyDB.COMPANY_ID;

public class CompanyAndDepartmentAndEmployeeDB {

    @Embedded
    public CompanyDB companyDB;

    @Relation(parentColumn = COMPANY_ID, entityColumn = COMPANY_ID, entity = DepatmentDB.class)
    public List<DepartmentAndEmployeeDB> departmentAndEmployees;

    @Override
    public String toString() {
        return "CompanyAndDepartmentAndEmployeeDB{" +
                "companyDB=" + companyDB +
                ", departmentAndEmployees=" + departmentAndEmployees +
                '}';
    }
}
