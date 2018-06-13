package com.vironit.correctapp.mvp.presentation.adapter.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.vironit.correctapp.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<Data, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    private final List<Data> mDataList = new ArrayList<>();

    protected BaseRecyclerViewAdapter() {
    }

    public void addData(@NonNull List<Data> dataItems) {
        AppLog.logObject(this);
        this.mDataList.addAll(dataItems);
        notifyItemRangeInserted(mDataList.size(), dataItems.size());
    }

    public void removeData() {
        AppLog.logObject(this);
        int size = mDataList.size();
        mDataList.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public int getItemCount() {
        AppLog.logObject(this);
        return mDataList.size();
    }

    public int getRealItemsCount() {
        AppLog.logObject(this);
        return getItemCount();
    }

    protected List<Data> getDataList() {
        AppLog.logObject(this);
        return mDataList;
    }
}
