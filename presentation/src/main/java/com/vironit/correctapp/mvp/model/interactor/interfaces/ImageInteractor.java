package com.vironit.correctapp.mvp.model.interactor.interfaces;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


public interface ImageInteractor {

    Single<ResponseBody> uploadImage(MultipartBody.Part body, RequestBody name);
}
