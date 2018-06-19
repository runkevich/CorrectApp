package com.vironit.correctapp.mvp.model.manager.interfaces;

import com.vironit.correctapp.mvp.model.manager.implementation.TockenManagerImpl;

public interface TockenManager {

     void saveTocken(Long dateRefresh,String refresh, String access);
     void deleteTocken(String tocken);
     TockenManagerImpl.ApiTocken getTocken();
     boolean isTockenValid();
}
