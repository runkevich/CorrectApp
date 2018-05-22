package com.vironit.correctapp.mvp.presentation.view.implementation.activity.base;


import android.content.Intent;
import android.os.Bundle;

import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;

import static com.vironit.correctapp.constans.AppConstans.LONG_DOUBLE_BACK_DELAYD;

public abstract class BaseDoubleBackActivity<P extends BaseAppPresenter> extends BaseActivity<P> {

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void handleDoesBack(){
        if (doubleBackToExitPressedOnce){
            startActivity(new Intent(this,DummyActivity.class));
            finish();
            doubleBackToExitPressedOnce=false;
        } else {

            doubleBackToExitPressedOnce = true;
            showAutoClosableMessage(getString(R.string.pressed_to_exit));
            mHandler.postDelayed(()-> doubleBackToExitPressedOnce = false,LONG_DOUBLE_BACK_DELAYD);
        }
    }

    @Override
    public void onBackPressed() {
        handleDoesBack();
    }
}
