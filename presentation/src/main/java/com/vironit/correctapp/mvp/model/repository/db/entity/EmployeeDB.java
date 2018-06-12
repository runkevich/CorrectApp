package com.vironit.correctapp.mvp.model.repository.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

import static com.vironit.correctapp.mvp.model.repository.db.entity.EmployeeDB.EMPLOYEE_LAST_NAME;
import static com.vironit.correctapp.mvp.model.repository.db.entity.EmployeeDB.EMPLOYEE_TABLE_NAME;

@Entity(tableName = EMPLOYEE_TABLE_NAME,
        foreignKeys = {@ForeignKey(entity = DepatmentDB.class,
                parentColumns = DepatmentDB.DEPARTMENT_ID,
                childColumns = DepatmentDB.DEPARTMENT_ID,
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)},
        indices = {@Index(value = EMPLOYEE_LAST_NAME,
                unique = false,
                name = "EMPLOYEE_LAST_NAME_INDEX")})

public class EmployeeDB {

    public static final String EMPLOYEE_TABLE_NAME = "EMPLOYEE";
    public static final String EMPLOYEE_ID = "EMPLOYEE_ID";
    public static final String EMPLOYEE_LAST_NAME = "EMPLOYEE_LAST_NAME";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = EMPLOYEE_ID)
    @Nullable
    private Long employeeId;

    @ColumnInfo(name = "EMPLOYEE_FIRST_NAME")
    @NonNull
    private String firstname;

    @ColumnInfo(name = EMPLOYEE_LAST_NAME)
    @NonNull
    private String lastname;

    @ColumnInfo(name = "EMPLOYEE_MIDDLE_NAME")
    @NonNull
    private String middlename;

    @ColumnInfo(name = "EMPLOYEE__DATE")
    @NonNull
    private Date date;

    @ColumnInfo(name = DepatmentDB.DEPARTMENT_ID)
    @NonNull
    private Long departmentId;

    @Embedded
    @NonNull
    private InsuranseDB insuranseDB;

    public EmployeeDB(Long employeeId,
                      @NonNull String firstname,
                      @NonNull String lastname,
                      @NonNull String middlename,
                      @NonNull Date date,
                      @NonNull Long departmentId,
                      @NonNull InsuranseDB insuranseDB) {
        this.employeeId = employeeId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.date = date;
        this.departmentId = departmentId;
        this.insuranseDB = insuranseDB;
    }

    @Nullable
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@Nullable Long employeeId) {
        this.employeeId = employeeId;
    }

    @NonNull
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(@NonNull Long departmentId) {
        this.departmentId = departmentId;
    }

    @NonNull
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NonNull String firstname) {
        this.firstname = firstname;
    }

    @NonNull
    public String getLastname() {
        return lastname;
    }

    public void setLastname(@NonNull String lastname) {
        this.lastname = lastname;
    }

    @NonNull
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(@NonNull String middlename) {
        this.middlename = middlename;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    @NonNull
    public InsuranseDB getInsuranseDB() {
        return insuranseDB;
    }

    public void setInsuranseDB(@NonNull InsuranseDB insuranseDB) {
        this.insuranseDB = insuranseDB;
    }
}
