package com.vironit.correctapp.mvp.model.repository.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.vironit.correctapp.mvp.model.repository.db.entity.DepatmentDB.DEPARTMENT_TABLE_NAME;

@Entity(tableName = DEPARTMENT_TABLE_NAME,
        foreignKeys = {@ForeignKey(entity = CompanyDB.class,
                parentColumns = CompanyDB.COMPANY_ID,
                childColumns = CompanyDB.COMPANY_ID,
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)})
public class DepatmentDB {

    public static final String DEPARTMENT_TABLE_NAME = "DEPARTMENT";
    public static final String DEPARTMENT_ID = "DEPARTMENT_ID";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DEPARTMENT_ID)
    @Nullable private Long departmentId;

    @ColumnInfo(name = CompanyDB.COMPANY_ID)
    @NonNull private Long companyId;

    @ColumnInfo(name = "DEPARTMENT_NAME")
    @NonNull private String name;

    public DepatmentDB() {
    }

    @Nullable
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(@Nullable Long departmentId) {
        this.departmentId = departmentId;
    }

    @NonNull
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(@NonNull Long companyId) {
        this.companyId = companyId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
