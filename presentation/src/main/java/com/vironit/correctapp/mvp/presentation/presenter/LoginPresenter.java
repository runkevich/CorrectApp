package com.vironit.correctapp.mvp.presentation.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.vironit.correctapp.App;
import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.mvp.model.interactor.interfaces.OauthInteractor;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ILoginView;
import com.vironit.correctapp.utils.AppLog;

import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class LoginPresenter extends BaseAppPresenter<ILoginView> {

    @Inject
    GoogleSignInClient mGoogleSignInClient;

    @Inject
    TwitterAuthClient mTwitterAuthClient;

    @Inject
    CallbackManager mCallbackManager;

    @Inject
    OauthInteractor mOauthInteractor;

    private String selectedLoginButton = "";
    String personEmail = "";
    String personId = "";
    String personName = "";
    String personPassword = "1111";

    public LoginPresenter() {
        App.getsAppComponent().inject(this);
    }

    @Override
    public void attachView(ILoginView view) {
        super.attachView(view);
    }

    public void clickOnFacebook(@NonNull Activity activity) {
        selectedLoginButton = AppConstants.FACEBOOK;
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.i("MY_APP_TAG", loginResult.getAccessToken().getUserId());
                        getViewState().goToHomeActivity();
                    }

                    @Override
                    public void onCancel() {
                        // TODO cancel for facebook
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.i("MY_APP_TAG", exception.toString());
                        getViewState().showFailMessage();
                    }
                });
    }

    public void clickOnGoogle(@NonNull Activity activity) {
        selectedLoginButton = AppConstants.GOOGLE;
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, AppConstants.RC_SIGN_IN);
    }

    public void clickOnTwitter(@NonNull Activity activity) {
        selectedLoginButton = AppConstants.TWITTER;
        mTwitterAuthClient.authorize(activity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                //getViewState().showSuccesMessage();
                // getViewState().goToHomeActivity();
            }

            @Override
            public void failure(TwitterException exception) {
                getViewState().showFailMessage();
            }
        });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.i("TAG", "Google");
            //getViewState().goToHomeActivity(); !!!!!!!1
        } catch (ApiException e) {

            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            getViewState().showFailMessage();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull BaseActivity activity) {
        super.onActivityResult(requestCode, resultCode, data, activity);
        switch (selectedLoginButton) {
            case AppConstants.TWITTER:
                mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
                break;
            case AppConstants.FACEBOOK:
                mCallbackManager.onActivityResult(requestCode, resultCode, data);
                break;
            case AppConstants.GOOGLE:
                if (requestCode == AppConstants.RC_SIGN_IN) {

                    GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(activity);

                    personEmail = acct.getEmail();
                    personName = acct.getDisplayName().toUpperCase();
                    String personGivenName = acct.getGivenName();
                    String personFamilyName = acct.getFamilyName();
                    personId = acct.getId();
                    Uri personPhoto = acct.getPhotoUrl();

                    getViewState().setInformationAccount(personName, personPassword);
                    authorize(personName, personPassword);
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    handleSignInResult(task);
                }
                break;
            default:
                break;
        }
    }

    public void signOutFromAllAccounts() {
        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession != null) {
            TwitterCore.getInstance().getSessionManager().clearActiveSession();
        }
        LoginManager.getInstance().logOut();
        mGoogleSignInClient.signOut();
    }

    @Override
    public void destroyView(ILoginView view) {
        super.destroyView(view);
        LoginManager.getInstance().unregisterCallback(mCallbackManager);
        mTwitterAuthClient.cancelAuthorize();
    }

//    public void checkAccount(@NonNull String text) {
//        String name = text.toUpperCase();
//
//        Single.just(mDatabaseReference.child(FirebaseConstants.USERS_JSON).orderByChild(FirebaseConstants.USER_NAME)
//                .equalTo(name).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot datas : dataSnapshot.getChildren()) {
//                            Log.i("LOG_TAG", "This accout has - from rx");
//                            getViewState().goToHomeActivity();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Log.i("LOG_TAG", "i don't know what a error - from rx");
//                    }
//                }))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnError(r -> Log.i("LOG_TAG", "i don't know what a error"));
    //   }

    public void authorize(String tEmailIn_s, String tPasswordIn_s) {

        mOauthInteractor.authorize(tEmailIn_s, tPasswordIn_s)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(success -> getViewState().goToHomeActivity())
                .doOnError(error -> getViewState().showAutoClosableMessage("Зарегистрируйтесь, такого пользователя не существует."))
                .subscribe(co -> AppLog.logPresenter(this, "OOOOOKKKKK4"),
                        co -> AppLog.logPresenter(this, "Not registration"));


       /* mOauthInteractor.authorize(tEmailIn_s, tPasswordIn_s)
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof CorrectAppException &&
                            ErrorStatus.USER_IS_NOT_EXIST == ((CorrectAppException) throwable).getErrorStatus()) {
                        Log.i("APP_LOG", ((CorrectAppException) throwable).getErrorStatus().toString());
                        getViewState().showAutoClosableMessage("Зарегистрируйтесь, такого пользователя не существует.");
                        return user -> error(throwable);
                    } else {
                        getViewState().goToHomeActivity(); //TODO check this
                        Log.i("APP_LOG", "Юзер есть");
                        return user -> Single.just(true);
                    }
                })
                .subscribe(co -> AppLog.logPresenter(this, "OOOOOKKKKK4"),
                        co -> AppLog.logPresenter(this, "Not registration"));*/
    }
}
