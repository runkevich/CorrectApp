package com.vironit.correctapp.mvp.model.repository.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vironit.correctapp.mvp.model.repository.db.converter.LongListToStringConverter;

import java.util.List;

import static com.vironit.correctapp.mvp.model.repository.db.entity.CompanyDB.COMPANY_TABLE_NAME;

@Entity(tableName = COMPANY_TABLE_NAME)
@TypeConverters(LongListToStringConverter.class)
public class CompanyDB {

    public static final String COMPANY_TABLE_NAME = "COMPANY";
    public static final String COMPANY_ID = "COMPANY_ID";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COMPANY_ID)
    @Nullable private Long companyId;

    @ColumnInfo(name = "COMPANY_NAME")
    @NonNull private Long name;

    @ColumnInfo(name = "COMPANY_SALARY")
    @NonNull private List<Long> salary;

    public CompanyDB(Long companyId, @NonNull Long name, @NonNull List<Long> salary) {
        this.companyId = companyId;
        this.name = name;
        this.salary = salary;
    }

    @Nullable
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(@Nullable Long companyId) {
        this.companyId = companyId;
    }

    @NonNull
    public Long getName() {
        return name;
    }

    public void setName(@NonNull Long name) {
        this.name = name;
    }

    @NonNull
    public List<Long> getSalary() {
        return salary;
    }

    public void setSalary(@NonNull List<Long> salary) {
        this.salary = salary;
    }
}
