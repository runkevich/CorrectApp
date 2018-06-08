package com.vironit.correctapp.mvp.model.interactor.implementation;

import com.vironit.correctapp.mvp.model.interactor.interfaces.ImageInteractor;
import com.vironit.correctapp.mvp.model.repository.dto.image.ImageData;
import com.vironit.correctapp.mvp.model.repository.interfaces.ImageRepository;

import javax.inject.Inject;

import io.reactivex.Single;


public class ImageInteractorImpl implements ImageInteractor {

    public final ImageRepository mImageRepository;

    @Inject
    public ImageInteractorImpl(ImageRepository mImageRepository) {
        this.mImageRepository = mImageRepository;
    }

    @Override
    public Single<ImageData> uploadImage(String image) {
        return mImageRepository.uploadImage(image);
    }
}
