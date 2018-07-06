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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.vironit.correctapp.App;
import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.constans.FirebaseConstants;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ILoginView;

import java.util.Arrays;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class LoginPresenter extends BaseAppPresenter<ILoginView> {

    @Inject
    GoogleSignInClient mGoogleSignInClient;

    @Inject
    TwitterAuthClient mTwitterAuthClient;

    @Inject
    CallbackManager mCallbackManager;

    DatabaseReference mDatabaseReference;

    private String selectedLoginButton = "";
    String personEmail = "";
    String personId = "";
    String personName = "";

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
                getViewState().goToHomeActivity();
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
            getViewState().goToHomeActivity();
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
                             personName = acct.getDisplayName();
                            String personGivenName = acct.getGivenName();
                            String personFamilyName = acct.getFamilyName();
                            personId = acct.getId();
                            Uri personPhoto = acct.getPhotoUrl();

                    Single.just(mDatabaseReference.child(FirebaseConstants.USERS_JSON))
                            .map(ref -> ref.push().getKey())
                            .flatMap(key -> RxFirebaseDatabase.setValue(mDatabaseReference.child(FirebaseConstants.USERS_JSON).child(key),
                                    new User(personEmail, personId, null, true))
                                    .toSingle(() -> true))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(ref -> Log.i("LOG_TAG", "YEP"),
                                    e -> Log.i("LOG_TAG", "NOPE"));
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

    public void clickOnGoogleFB(@NonNull Activity activity){

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        selectedLoginButton = AppConstants.GOOGLE;
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, AppConstants.RC_SIGN_IN);
    }
}
