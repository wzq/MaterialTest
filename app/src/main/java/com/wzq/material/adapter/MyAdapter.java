package com.wzq.material.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzq.material.EasyMap;
import com.wzq.material.R;

import java.util.List;

/**
 * Created by wzq on 15/6/3.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<EasyMap> data;

    public MyAdapter(List<EasyMap> data){
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EasyMap item = data.get(position);
        MyHolder mHolder = (MyHolder) holder;
        mHolder.title.setText(item.getString("title"));
        mHolder.content.setText(item.getString("content"));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        public ImageView head;

        public TextView title, content;

        public MyHolder(View itemView) {
            super(itemView);
            head = (ImageView) itemView.findViewById(R.id.main_head);
            title = (TextView) itemView.findViewById(R.id.main_title);
            content = (TextView) itemView.findViewById(R.id.main_content);
        }
    }
}
