package com.vironit.correctapp.mvp.model.repository.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.image.ImageData;

import io.reactivex.Single;

public interface ImageRepository {

    Single<ImageData> uploadImage(String image);
}
