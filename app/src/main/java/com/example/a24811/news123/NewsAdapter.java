package com.example.a24811.news123;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by 24811 on 2017/11/10.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mcontext;
    private NewsListBean newsListBean;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.news_title);
            imageView = (ImageView) itemView.findViewById(R.id.news_image);
        }
    }

    public NewsAdapter(Context context, NewsListBean newsListBean) {
        this.mcontext = context;
        this.newsListBean = newsListBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.news_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(newsListBean.data.get(position).subject);
        Glide.with(mcontext)
                .load(newsListBean.data.get(position).pic)
                .centerCrop()
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle mbundle = new Bundle();
                mbundle.putString("text" ,newsListBean.data.get(position).index);
                intent.setClass(mcontext , NewsActivity.class);
                intent.putExtras(mbundle);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsListBean.data.size();
    }
}
