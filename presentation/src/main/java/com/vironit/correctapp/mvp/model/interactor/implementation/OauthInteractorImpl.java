package com.vironit.correctapp.mvp.model.interactor.implementation;

import android.util.Log;

import com.vironit.correctapp.mvp.model.interactor.interfaces.OauthInteractor;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;
import com.vironit.correctapp.mvp.model.repository.interfaces.OauthRepository;
import com.vironit.correctapp.utils.ErrorHandlerUtil;
import com.vironit.correctapp.utils.exception.CorrectAppException;
import com.vironit.correctapp.utils.exception.ErrorStatus;

import javax.inject.Inject;

import io.reactivex.Single;

import static io.reactivex.Single.error;

public class OauthInteractorImpl implements OauthInteractor {

    private final OauthRepository mOauthRepository;

    @Inject
    public OauthInteractorImpl(OauthRepository mOauthRepository) {
        this.mOauthRepository = mOauthRepository;
    }

    @Override
    public Single<User> register(String name, String email, String password) {
        return mOauthRepository.getUserByNameOrEmail(name, email)
                .flatMap(user -> Single.<User>error(new CorrectAppException(ErrorStatus.USER_EXIST)))
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof CorrectAppException &&
                            ErrorStatus.USER_IS_NOT_EXIST == ((CorrectAppException) throwable).getErrorStatus()) {
                        Log.i("APP_LOG", ((CorrectAppException) throwable).getErrorStatus().toString());
                        return mOauthRepository.createNewUser(name, email, password);
                    } else {
                        return error(throwable);
                    }
                });
    }

    @Override
    public Single<Boolean> authorize(String nameOrEmail, String password) {
        return mOauthRepository.authorize(nameOrEmail, password)
                .onErrorResumeNext(ErrorHandlerUtil::defaultHandle);
    }
        /* mOauthRepository.getUserByNameOrEmail(nameOrEmail, nameOrEmail)
                .flatMap(user -> {
                            //mOauthRepository.saveUserToSharedPref(nameOrEmail);
                            //mOauthRepository.saveThisUser(user);
                            return Single.<User>error(new CorrectAppException(ErrorStatus.USER_EXIST));
                        }
                )
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof CorrectAppException &&
                            ErrorStatus.USER_IS_NOT_EXIST == ((CorrectAppException) throwable).getErrorStatus()) {
                        return error(throwable);
                    } else {
                        return error(throwable);
                    }
                });
    }*/
}