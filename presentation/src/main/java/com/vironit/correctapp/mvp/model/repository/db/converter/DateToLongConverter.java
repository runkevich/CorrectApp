package com.vironit.correctapp.mvp.model.repository.db.converter;

import android.arch.persistence.room.TypeConverter;
import android.support.annotation.Nullable;

import java.util.Date;

public class DateToLongConverter {

    @TypeConverter
    public Long fromDateToLong(@Nullable Date date) {
        Long time = null;
        if (date != null) {
            time = date.getTime();
        }
        return time;
    }

    @TypeConverter
    public Date fromLongToData(@Nullable Long longs) {
        Date date = null;
        if (longs != null) {
            date = new Date(longs);
        }
        return date;
    }
}
