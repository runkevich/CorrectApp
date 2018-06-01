package com.vironit.correctapp.mvp.model.interactor.implementation;

import com.vironit.correctapp.mvp.model.interactor.interfaces.ImageInteractor;
import com.vironit.correctapp.mvp.model.repository.interfaces.ImageRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


public class ImageInteractorImpl implements ImageInteractor {

    public final ImageRepository mImageRepository;

    @Inject
    public ImageInteractorImpl(ImageRepository mImageRepository) {
        this.mImageRepository = mImageRepository;
    }

    @Override
    public Single<ResponseBody> uploadImage(MultipartBody.Part body, RequestBody name) {
        return mImageRepository.uploadImage(body,name);
    }
}
