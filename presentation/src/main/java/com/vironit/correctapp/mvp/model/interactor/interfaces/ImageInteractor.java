package com.vironit.correctapp.mvp.model.interactor.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.image.ImageData;

import io.reactivex.Single;


public interface ImageInteractor {

    Single<ImageData> uploadImage(String image );
}
