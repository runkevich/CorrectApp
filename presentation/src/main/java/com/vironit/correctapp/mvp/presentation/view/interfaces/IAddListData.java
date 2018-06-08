package com.vironit.correctapp.mvp.presentation.view.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.Data;

import java.util.List;

public interface IAddListData<T> {

   void addDataList(List<Data> dataList);
}
