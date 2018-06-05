package com.vironit.correctapp.mvp.presentation.adapter.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.vironit.correctapp.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<Data, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final List<Data> mData = new ArrayList<>();

    protected List<Data> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        AppLog.logObject(this);
        return mData.size();
    }

    public void addData(@NonNull List<Data> data) {
        AppLog.logObject(this);
        int curSize = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(curSize, data.size());
    }

    public void removeData() {
        AppLog.logObject(this);
        int size = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0, size);
    }
}
