package com.vironit.correctapp.mvp.model.repository.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.vironit.correctapp.BuildConfig;
import com.vironit.correctapp.mvp.model.repository.db.converter.DateToLongConverter;
import com.vironit.correctapp.mvp.model.repository.db.dao.CompanyAndDepartmentAndEmployeeDAO;
import com.vironit.correctapp.mvp.model.repository.db.dao.CompanyDAO;
import com.vironit.correctapp.mvp.model.repository.db.dao.CompanyDepartmentDAO;
import com.vironit.correctapp.mvp.model.repository.db.dao.DepartmentDAO;
import com.vironit.correctapp.mvp.model.repository.db.dao.EmployeeDAO;
import com.vironit.correctapp.mvp.model.repository.db.entity.CompanyDB;
import com.vironit.correctapp.mvp.model.repository.db.entity.DepatmentDB;
import com.vironit.correctapp.mvp.model.repository.db.entity.EmployeeDB;

@TypeConverters(DateToLongConverter.class)
@Database(entities = {CompanyDB.class, DepatmentDB.class, EmployeeDB.class},
        version = BuildConfig.DATABASE_VERSION)
public abstract class CorrectDatabase extends RoomDatabase {

    public abstract CompanyDAO getCompanyDAO();

    public abstract DepartmentDAO getDepartmentDAO();

    public abstract EmployeeDAO getEmployeeDAO();

    public abstract CompanyDepartmentDAO getCompanyDepartmentDAO();

    public abstract CompanyAndDepartmentAndEmployeeDAO getCompanyAndDepartmentAndEmployeeDAO();
}
//1) Insert ( Replace Ignore)
//2) SELECT - все WHERE ID
//3)              WHERE LAST NAME (у юзера)
//4)* по Id - создать модель
//  Company + Depatment
//  Depatment + Employee
//
//5) во всех репозиториях ставить onError
//6)  Flowable как Observable, MayBe как Single
//7) Обратный SELECT
//  от employee -> department -> company по ID