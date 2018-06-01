package com.vironit.correctapp.mvp.model.repository.implementation;

import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.mvp.model.repository.ApiInterface;
import com.vironit.correctapp.mvp.model.repository.interfaces.ImageRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ImageRepositoryImpl implements ImageRepository {

    private final ApiInterface mApiInterface;
    private final Scheduler mIOScheduler;

    @Inject
    public ImageRepositoryImpl(ApiInterface mApiInterface,
                               @Named(AppConstants.IO_SCHEDULER) Scheduler mIOScheduler) {
        this.mApiInterface = mApiInterface;
        this.mIOScheduler = mIOScheduler;
    }

    @Override
    public Single<ResponseBody> uploadImage(MultipartBody.Part image, RequestBody name) {
        return mApiInterface.uploadImage(image,name)
                .subscribeOn(mIOScheduler);
    }
}
