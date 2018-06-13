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

//    private Context mContext;
//    private RequestOptions mNewsImageRequestOptions;
//
//    public NewsAdapter(@NonNull Context context) {
//        AppLog.logObject(this);
//        mContext = context;
//        mNewsImageRequestOptions = new RequestOptions()
//                .fallback(new ColorDrawable(Color.RED));
//    }
//
//    @NonNull
//    @Override
//    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        AppLog.logObject(this);
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_news, parent, false);
//        return new NewsAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
//        AppLog.logObject(this);
//        Article article = null
//                /*= getData().get(position)*/;
//        holder.mSourceName.setText(article.getSource().getName());
//        Glide.with(mContext)
//                .load(article.getUrlToImage())
//                .apply(mNewsImageRequestOptions)
//                .into(holder.mImageView);
//        holder.mTitle.setText(article.getTitle());
//        holder.mDescription.setText(article.getDescription());
//        holder.mButton.setTag(article.getUrl());
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//
//        @BindView(R.id.tv_i_news_source_name)
//        TextView mSourceName;
//        @BindView(R.id.iv_i_news_image)
//        ImageView mImageView;
//        @BindView(R.id.tv_i_news_title)
//        TextView mTitle;
//        @BindView(R.id.tv_i_news_description)
//        TextView mDescription;
//        @BindView(R.id.btn_i_news_read_more)
//        Button mButton;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            AppLog.logObject(this);
//            ButterKnife.bind(this, itemView);
//            mButton.setOnClickListener(view -> {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse((String) view.getTag()));
//                mContext.startActivity(intent);
//            });
//        }
//    }
}