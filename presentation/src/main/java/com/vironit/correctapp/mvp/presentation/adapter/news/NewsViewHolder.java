package com.vironit.correctapp.mvp.presentation.adapter.news;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.model.repository.dto.Article;
import com.vironit.correctapp.mvp.presentation.adapter.base.BaseViewHolder;

import butterknife.BindView;

public class NewsViewHolder extends BaseViewHolder<Article> {

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

    public NewsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(Article article) {
        mSourceName.setText(article.getSource().getName());
        Glide.with(itemView.getContext())
                .load(article.getUrlToImage())
                .into(mImageView);
        mTitle.setText(article.getTitle());
        mDescription.setText(article.getDescription());
        mButton.setTag(article.getUrl());
    }
}
