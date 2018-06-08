package com.vironit.correctapp.mvp.model.repository.implementation;

import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.mvp.model.repository.ApiInterfaceImage;
import com.vironit.correctapp.mvp.model.repository.dto.image.ImageData;
import com.vironit.correctapp.mvp.model.repository.dto.image.ImageRequest;
import com.vironit.correctapp.mvp.model.repository.interfaces.ImageRepository;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class ImageRepositoryImpl implements ImageRepository {

    private final ApiInterfaceImage mApiInterfaceImage;
    private final Scheduler mIOScheduler;

    @Inject
    public ImageRepositoryImpl(ApiInterfaceImage mApiInterfaceImage,
                               @Named(AppConstants.IO_SCHEDULER) Scheduler mIOScheduler) {
        this.mApiInterfaceImage = mApiInterfaceImage;
        this.mIOScheduler = mIOScheduler;
    }

    @Override
    public Single<ImageData> uploadImage(String image) {
        ImageRequest requestBody = new ImageRequest(image);
        return sendImageRequest(requestBody);
    }

    private Single<ImageData> sendImageRequest(ImageRequest requestBody) {
        return mApiInterfaceImage.uploadImage(
                getFilePart(requestBody.getFile()),
                getStringPart("api_key", requestBody.getApiKey()),
                getStringPart("timestamp", requestBody.getTimestamp()),
                getStringPart("signature", requestBody.getSignature()))
                .subscribeOn(mIOScheduler);
    }

    private MultipartBody.Part getFilePart(File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData("file", file.getName(), requestFile);
    }

    private MultipartBody.Part getStringPart(String name, String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return MultipartBody.Part.createFormData(name, value);
    }
}