package com.vironit.correctapp.mvp.model.repository;

import com.vironit.correctapp.mvp.model.repository.dto.image.ImageData;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterfaceImage {

    @Multipart
    @POST("image/upload/")
    Single<ImageData> uploadImage(@Part MultipartBody.Part image,
                                  @Part MultipartBody.Part api,
                                  @Part MultipartBody.Part timestamp,
                                  @Part MultipartBody.Part sha1);
}
