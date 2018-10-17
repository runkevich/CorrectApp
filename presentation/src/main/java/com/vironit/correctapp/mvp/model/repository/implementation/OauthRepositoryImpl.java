package com.vironit.correctapp.mvp.model.repository.implementation;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.constans.FirebaseConstants;
import com.vironit.correctapp.constans.SharedPreferencesConstants;
import com.vironit.correctapp.mvp.model.repository.ApiOauthInterface;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;
import com.vironit.correctapp.mvp.model.repository.interfaces.OauthRepository;
import com.vironit.correctapp.utils.exception.CorrectAppException;
import com.vironit.correctapp.utils.exception.ErrorStatus;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;
import javax.inject.Named;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class OauthRepositoryImpl implements OauthRepository {

    //private TockenManager mTockenManager;
    private final AtomicBoolean mIsTockenUpdatingNow = new AtomicBoolean(false);
    private Scheduler mScheduler;
    private ApiOauthInterface mApiOauthInterface;
    private SharedPreferences mSharedPreferences;

    private DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.USERS_JSON);

    @Inject
    public OauthRepositoryImpl(@Named(value = AppConstants.IO_SCHEDULER) Scheduler mScheduler,
                               ApiOauthInterface mApiOauthInterface,
                               SharedPreferences mSharedPreferences) {
        this.mScheduler = mScheduler;
        this.mApiOauthInterface = mApiOauthInterface;
        this.mSharedPreferences = mSharedPreferences;
    }

//    private Single<Boolean> updateTocken() {
//        if (mIsTockenUpdatingNow.get()) {
//            return Single.timer(100, TimeUnit.MILLISECONDS, mScheduler)
//                    .flatMap(aLong -> updateTockenIfNeeded());//retry
//        } else {
//            return Single.fromCallable(() -> {
//                mIsTockenUpdatingNow.set(true);
//
//                return true;
//                // todo return mApiAuthInterface;
//            })
//                    .flatMap(apiAuthInterface -> Single.just(true)
//                            //todo get from network
//                    )
//                    .map(newToken -> true
//                            // todo save new updated token to manager
//                    )
//                    .map(aBoolean -> mIsTockenUpdatingNow.getAndSet(false));
//        }
//    }
//
//    @Override
//    public Single<Boolean> updateTockenIfNeeded() {
//        if (mTockenManager.isTockenValid()) {
//            return Single.just(true);
//        } else return updateTocken();
//    }

    @Override
    public Single<User> createNewUser(String name, String email, String password) {
        return Single.just(mDatabaseReference)
                .subscribeOn(mScheduler)
                .map(ref -> ref.push().getKey())
                .flatMap(key -> {
                    // @Nullable String key = mDatabaseReference.getKey();
                    // if (key != null) {
                    User user = new User(name, key, null, true, email, password);
                    saveThisUser(user);
                    return RxFirebaseDatabase.setValue(mDatabaseReference.child(key),
                            user)
                            .toSingle(() -> user);
                    //  } else {
                    //  return Single.error(new CorrectAppException(R.string.database_connect_error));
                    // }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(ref -> Log.i("LOG_TAG", "YEP"))
                .doOnError(ref -> Log.i(
                        "LOG_TAG", "NOOOO"));
    }

    @Override
    public Single<Boolean> authorize(String nameOrEmail, String password) {
        return getUserByNameOrEmail(nameOrEmail, nameOrEmail)
                .flatMap(user -> {
                    Log.i("APP_LOG", "зашел");
                    saveThisUser(user);
                    Single.<Boolean>error(new CorrectAppException(ErrorStatus.USER_EXIST));
                    return Single.just(true);
                })
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof CorrectAppException &&
                            ErrorStatus.USER_IS_NOT_EXIST == ((CorrectAppException) throwable).getErrorStatus()) {
                        Log.i("APP_LOG", ((CorrectAppException) throwable).getErrorStatus().toString());
                        return Single.error(throwable);
                    } else {
                        Log.i("APP_LOG", "Юзер есть");
                        return Single.just(true);
                    }
                });
    }

    /* return getUserByNameOrEmail(nameOrEmail, nameOrEmail)
    .flatMap(user -> Single.<Boolean>error(new CorrectAppException(ErrorStatus.USER_EXIST)))
            .onErrorResumeNext(throwable -> {
        if (throwable instanceof CorrectAppException &&
                ErrorStatus.USER_IS_NOT_EXIST == ((CorrectAppException) throwable).getErrorStatus()) {
            Log.i("APP_LOG", ((CorrectAppException) throwable).getErrorStatus().toString());
            return Single.just(true);
        } else {
            Log.i("APP_LOG", "Юзер есть");
            return Single.just(false);
        }
    });*/
    @Override
    public Single<User> getUserByNameOrEmail(String name, String email) {
        return getUserByName(name)
                .onErrorResumeNext(throwable -> throwable instanceof NoSuchElementException ? (getUserByEmail(email))
                        .onErrorResumeNext(throwablel -> throwablel instanceof NoSuchElementException ? Single.error(new CorrectAppException(ErrorStatus.USER_IS_NOT_EXIST))
                                : Single.error(throwablel))
                        : Single.error(throwable));
    }

    @Override
    public Single<User> getUserByEmail(String email) {
        return RxFirebaseDatabase.observeSingleValueEvent(mDatabaseReference.orderByChild(FirebaseConstants.USER_EMAIL).equalTo(email),
                dataSnapshot -> dataSnapshot.getChildren().iterator().next().getValue(User.class))
                .toSingle()
                .subscribeOn(mScheduler);
    }

    @Override
    public Single<User> getUserByName(String name) {
        return RxFirebaseDatabase.observeSingleValueEvent(mDatabaseReference.orderByChild(FirebaseConstants.USER_NAME).equalTo(name),
                dataSnapshot -> dataSnapshot.getChildren().iterator().next().getValue(User.class))
                .toSingle()
                .subscribeOn(mScheduler);
    }

    @Override
    public void saveThisUser(@NonNull User user) {
        mSharedPreferences.edit().putString(SharedPreferencesConstants.USER_NAME, user.getUserName()).apply();
        mSharedPreferences.edit().putString(SharedPreferencesConstants.USER_ID, user.getUserId()).apply();
        mSharedPreferences.edit().putString(SharedPreferencesConstants.USER_EMAIL, user.getUserEmail()).apply();
    }

    @Override
    public String getThisUserId() {
        return mSharedPreferences.getString(SharedPreferencesConstants.USER_ID, "");
    }

    @Override
    public String getThisUserName() {
        return mSharedPreferences.getString(SharedPreferencesConstants.USER_NAME, "");
    }

    @Override
    public void saveUserToSharedPref(String nameOrEmail) {
        RxFirebaseDatabase.observeSingleValueEvent(mDatabaseReference.orderByChild(FirebaseConstants.USER_EMAIL).equalTo(nameOrEmail),
                dataSnapshot -> dataSnapshot.getValue(User.class))
                .subscribeOn(mScheduler)
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(user -> {
                    Log.i("LOG_TAG", user.getUserId());
                });
    }
}