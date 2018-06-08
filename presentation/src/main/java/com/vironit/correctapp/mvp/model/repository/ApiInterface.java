package com.vironit.correctapp.mvp.model.repository;

import com.vironit.correctapp.mvp.model.repository.dto.Data;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Single<Data> getNews(@Query("country") String countryCode);
}
