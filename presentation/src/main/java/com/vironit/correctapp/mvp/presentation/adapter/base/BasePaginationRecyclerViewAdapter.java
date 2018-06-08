package com.vironit.correctapp.mvp.presentation.adapter.base;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

public abstract class BasePaginationRecyclerViewAdapter<Data, VH extends RecyclerView.ViewHolder>
        extends BaseRecyclerViewAdapter<Data, VH> {

    public @Nullable String getLastItemId() {
        return null;
    }
}
