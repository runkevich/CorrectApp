package com.vironit.correctapp.mvp.model.repository;

import com.vironit.correctapp.mvp.model.repository.dto.Data;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Single<Data> getNews(@Query("country") String countryCode);

    @Multipart
    @POST("/")
    Single<ResponseBody> uploadImage(@Part MultipartBody.Part image,
                                     @Part("name") RequestBody name);
    //api, timestamp, sha1
}
