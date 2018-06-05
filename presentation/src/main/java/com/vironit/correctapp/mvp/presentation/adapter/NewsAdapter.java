package com.vironit.correctapp.mvp.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.model.repository.dto.Article;
import com.vironit.correctapp.mvp.presentation.adapter.base.BaseRecyclerViewAdapter;
import com.vironit.correctapp.utils.AppLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends BaseRecyclerViewAdapter<Article,NewsAdapter.ViewHolder> {

    private Context mContext;
    private RequestOptions mNewsImageRequestOptions;

    public NewsAdapter(@NonNull Context context) {
        AppLog.logObject(this);
        mContext = context;
        mNewsImageRequestOptions = new RequestOptions()
                .fallback(new ColorDrawable(Color.RED));
    }

    @NonNull @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AppLog.logObject(this);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        AppLog.logObject(this);
        Article article = getData().get(position);
        holder.mSourceName.setText(article.getSource().getName());
        Glide.with(mContext)
                .load(article.getUrlToImage())
                .apply(mNewsImageRequestOptions)
                .into(holder.mImageView);
        holder.mTitle.setText(article.getTitle());
        holder.mDescription.setText(article.getDescription());
        holder.mButton.setTag(article.getUrl());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_i_news_source_name)
        TextView mSourceName;
        @BindView(R.id.iv_i_news_image)
        ImageView mImageView;
        @BindView(R.id.tv_i_news_title)
        TextView mTitle;
        @BindView(R.id.tv_i_news_description)
        TextView mDescription;
        @BindView(R.id.btn_i_news_read_more)
        Button mButton;

        ViewHolder(View itemView) {
            super(itemView);
            AppLog.logObject(this);
            ButterKnife.bind(this, itemView);
            mButton.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse((String) view.getTag()));
                mContext.startActivity(intent);
            });
        }
    }
}
