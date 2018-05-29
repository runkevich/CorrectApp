package com.vironit.correctapp.mvp.model.interactor.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.Data;

import io.reactivex.Single;

public interface NewsInteractor {

    Single<Data> getNews();
}
