package com.vironit.correctapp.mvp.model.manager.implementation;

import com.vironit.correctapp.mvp.model.manager.interfaces.TockenManager;

import javax.inject.Inject;

public class TockenManagerImpl implements TockenManager {
    @Inject
    public TockenManagerImpl() {
    }
    //
//    protected Gson gson;
//    protected SharedPreferences sharedPreferences;
//    private static Long tockenRunTime = 90 * 60 * 1000L;
//    private ApiTocken mApiTocken = getTocken();
//
//
//    private volatile String refresh;
//    private volatile String access;
//
//    @Inject
//    public TockenManagerImpl(Gson gson, SharedPreferences sharedPreferences) {
//        this.gson = gson;
//        this.sharedPreferences = sharedPreferences;
//    }
//
//    @Override
//    public void saveTocken(Long dateRefresh, String refresh, String access) {
//        ApiTocken apiTocken = new ApiTocken(dateRefresh, refresh, access);
//        String strinnn = gson.toJson(apiTocken);
//        sharedPreferences.edit().putString(AppConstants.TOCKEN_KEY, strinnn).apply();
//    }
//
//    @Override
//    public void deleteTocken(String tocken) {
//        sharedPreferences.edit().remove(AppConstants.TOCKEN_KEY).apply();
//    }
//
//    @Override
//    public ApiTocken getTocken() {
//        String dateT = sharedPreferences.getString(AppConstants.TOCKEN_KEY, "7");
//        if (TextUtils.isEmpty(dateT)) {
//            return gson.fromJson(dateT, ApiTocken.class); //TODO
//        } else {
//            return gson.fromJson(dateT, ApiTocken.class);
//        }
//
//    }
//
//    @Override
//    public boolean isTockenValid() {
//        if (mApiTocken != null && mApiTocken.access != null && mApiTocken.refresh != null) {
//            if ((System.currentTimeMillis() - mApiTocken.creationDate) < tockenRunTime) {
//                return true;
//            } else
//                return false;
//        } else
//            return false;
//    }
//
//
//    public class ApiTocken {
//        private Long creationDate;
//        private String refresh;
//        private String access;
//
//        public ApiTocken(Long creationDate, String refresh, String access) {
//            this.creationDate = creationDate;
//            this.refresh = refresh;
//            this.access = access;
//        }
//
//        public Long getCreationDate() {
//            return creationDate;
//        }
//
//        public String getRefresh() {
//            return refresh;
//        }
//
//        public String getAccess() {
//            return access;
//        }
//
//
//    }
}
