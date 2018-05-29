package com.vironit.correctapp.mvp.model.repository.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.Data;

import io.reactivex.Single;

public interface NewsRepository {

    Single<Data> getNews(String countryCode);
}
