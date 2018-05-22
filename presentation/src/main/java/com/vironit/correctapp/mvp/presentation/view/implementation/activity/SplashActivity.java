package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

public class SplashActivity extends MvpAppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //defaultLoginTwitter();
        startActivity(new Intent(this, LoginActivity.class));


    }

//    private void defaultLoginTwitter() {
//
//    }
}
