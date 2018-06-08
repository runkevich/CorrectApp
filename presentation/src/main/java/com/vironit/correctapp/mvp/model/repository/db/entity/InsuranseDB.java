package com.vironit.correctapp.mvp.model.repository.db.entity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;


public class InsuranseDB {

    @Nullable private Long insuranseId;
    @NonNull private Long insuranseCompanyId;
    @NonNull private String insuranseCompanyName;
    @NonNull private Date dateStart;
    @NonNull private Date dateStop;

    public InsuranseDB(Long insuranseId,
                       @NonNull Long insuranseCompanyId,
                       @NonNull String insuranseCompanyName,
                       @NonNull Date dateStart,
                       @NonNull Date dateStop) {
        this.insuranseId = insuranseId;
        this.insuranseCompanyId = insuranseCompanyId;
        this.insuranseCompanyName = insuranseCompanyName;
        this.dateStart = dateStart;
        this.dateStop = dateStop;
    }

    @Nullable
    public Long getInsuranseId() {
        return insuranseId;
    }

    public void setInsuranseId(@Nullable Long insuranseId) {
        this.insuranseId = insuranseId;
    }

    @NonNull
    public Long getInsuranseCompanyId() {
        return insuranseCompanyId;
    }

    public void setInsuranseCompanyId(@NonNull Long insuranseCompanyId) {
        this.insuranseCompanyId = insuranseCompanyId;
    }

    @NonNull
    public String getInsuranseCompanyName() {
        return insuranseCompanyName;
    }

    public void setInsuranseCompanyName(@NonNull String insuranseCompanyName) {
        this.insuranseCompanyName = insuranseCompanyName;
    }

    @NonNull
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(@NonNull Date dateStart) {
        this.dateStart = dateStart;
    }

    @NonNull
    public Date getDateStop() {
        return dateStop;
    }

    public void setDateStop(@NonNull Date dateStop) {
        this.dateStop = dateStop;
    }
}
