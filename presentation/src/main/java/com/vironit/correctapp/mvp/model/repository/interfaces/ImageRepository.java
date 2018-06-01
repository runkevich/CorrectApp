package com.vironit.correctapp.mvp.model.repository.interfaces;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public interface ImageRepository {

    Single<ResponseBody> uploadImage(MultipartBody.Part image, RequestBody name);
}
