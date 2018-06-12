package com.vironit.correctapp.mvp.model.repository.db.converter;

import android.arch.persistence.room.TypeConverter;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LongListToStringConverter {

    @TypeConverter
    public String fromLongListToString(@Nullable List<Long> longs) {
        String stroks = null;
        if (longs != null) {
            stroks = longs.toString();
        }
        return stroks;
    }

    @TypeConverter
    public List<Long> fromStringToLongList(@Nullable String stroks) {
        List<Long> list = null;
        if (stroks != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                list = Stream.of(stroks.split(","))
                        .map(Long::parseLong)
                        .collect(Collectors.toList());
            }
        }
        return list;
    }
}
