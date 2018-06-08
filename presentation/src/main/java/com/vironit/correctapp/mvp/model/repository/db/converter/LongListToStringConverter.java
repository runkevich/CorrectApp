package com.vironit.correctapp.mvp.model.repository.db.converter;

import android.arch.persistence.room.TypeConverter;
import android.support.annotation.Nullable;

import java.util.List;

public class LongListToStringConverter {

    @TypeConverter
    public String fromLongListToString(@Nullable List<Long> longs){
        String stroks = null;
        if (longs != null){
           stroks = longs.toString();
        }
        return stroks;
    }

    @TypeConverter
    public List<Long> fromStringToLongList(@Nullable String stroks){
        return null;
    }
}
