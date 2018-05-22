package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

public class SplashActivity extends MvpAppCompatActivity{

   // @InjectPresenter
  //  SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, TestActivity.class));
    }
}
