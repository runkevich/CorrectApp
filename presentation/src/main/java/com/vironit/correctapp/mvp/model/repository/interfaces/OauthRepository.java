package com.vironit.correctapp.mvp.model.repository.interfaces;

import io.reactivex.Single;

public interface OauthRepository {

    Single<Boolean> updateTockenIfNeeded();
}
