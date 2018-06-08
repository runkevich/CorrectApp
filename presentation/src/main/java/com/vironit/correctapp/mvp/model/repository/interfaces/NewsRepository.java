package com.vironit.correctapp.mvp.model.repository.interfaces;

import android.support.annotation.IntRange;

import com.vironit.correctapp.mvp.model.repository.dto.Data;

import io.reactivex.Single;

public interface NewsRepository {

    Single<Data> getNews(String countryCode,
                         @IntRange(from = 1) int page,
                         @IntRange(from = 1, to = 100) int pageSize);
}
