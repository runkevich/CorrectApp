package com.vironit.correctapp.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vironit.correctapp.BuildConfig;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePresenter;

public abstract class AppLog {

    private String APP_TAG = "APP_TAG";

    public static void logPresenter(@NonNull BasePresenter presenter){
        //TODO
        if (isLogEnabled()){
            Log.i("App_Tag","App_Tag");
        }
    }
    public static void logActivity(@NonNull Activity activity){

        if (isLogEnabled()){
            Log.i("App_Tag","App_Tag");
        }
    }

    private static boolean isLogEnabled(){

        return BuildConfig.DEBUG; //из своего проекта
    }

    private static String getMethodName(){
        try {
                return Thread.currentThread().getStackTrace()[4].getMethodName();

        } catch (Exception e){

            return "unknownMethod";

        }

    }

    private static String getClassName(Object object){
      return   object.getClass().getName();
    }


    private static String getInfo(Object object){
        return "Class" + getClassName(object) + "Method: " + getMethodName();
    }

    private  String getAPP_TAG(){
     return APP_TAG;
    }
}


