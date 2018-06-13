package com.vironit.correctapp.mvp.model.repository.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import static com.vironit.correctapp.mvp.model.repository.db.entity.DepatmentDB.DEPARTMENT_ID;

public class DepartmentAndEmployeeDB {

    @Embedded
    public DepatmentDB depatment;

    @Relation(parentColumn = DEPARTMENT_ID, entityColumn = DEPARTMENT_ID)
    public List<EmployeeDB> depatments;

    @Override
    public String toString() {
        return "DepartmentAndEmployeeDB{" +
                "depatment=" + depatment +
                ", depatments=" + depatments +
                '}';
    }
}
