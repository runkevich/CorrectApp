package com.vironit.correctapp.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vironit.correctapp.mvp.model.repository.dto.Data;
import com.vironit.correctapp.mvp.presentation.view.interfaces.RecyclerItemClickListener;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterRv extends RecyclerView.Adapter<AdapterRv.ExampleHolder> {

    private ArrayList<Data> newsArrayList;
    private int itemLayout;
    private RecyclerItemClickListener recyclerItemClickListener;

    public void setRecyclerItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public AdapterRv(ArrayList<Data> newsArrayList, int itemLayout) {
        this.newsArrayList = newsArrayList;
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleHolder holder, final int position) {
        final Data data = newsArrayList.get(position);
       // holder.title.setText(picture.getName());
       // holder.imageView.setImageResource(picture.getImage());
    }


    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }


    public class ExampleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //@BindView(R.id.txt_title)
       // TextView title;
       // @BindView(R.id.imageView)
       // ImageView imageView;

        ExampleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (recyclerItemClickListener != null)
                recyclerItemClickListener.onItemClickListener(getAdapterPosition());
        }
    }
}
