package com.vironit.correctapp.mvp.presentation.adapter.news;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.model.repository.dto.Article;
import com.vironit.correctapp.mvp.presentation.adapter.base.BasePaginationRecyclerViewAdapter;
import com.vironit.correctapp.mvp.presentation.adapter.base.BaseViewHolder;

public class NewsAdapter extends BasePaginationRecyclerViewAdapter<Article, BaseViewHolder> {

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View postView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindView(getDataList().get(position));
    }
}