package com.wzq.material.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.wzq.utils.EasyMap;
import com.wzq.material.R;

import java.util.List;

/**
 * Created by wzq on 15/6/3.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private Context context;

    private List<EasyMap> data;

    private View.OnClickListener itemListener;


    public MyAdapter(Context context, List<EasyMap> data, View.OnClickListener itemListener) {
        this.context = context;
        this.data = data;
        this.itemListener = itemListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        EasyMap item = data.get(position);
        final MyHolder mHolder = (MyHolder) holder;
        mHolder.title.setText(item.getString("title"));
        mHolder.content.setText(item.getString("content"));
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), item.getInt("picture", R.mipmap.ic_launcher));
        mHolder.picture.setImageBitmap(bitmap);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                if (palette.getVibrantSwatch() != null) {
                    mHolder.picture.setBackgroundColor(palette.getVibrantSwatch().getRgb());
                } else {
                    System.out.println(position);
                    for (Palette.Swatch swatch : palette.getSwatches()) {
                        mHolder.picture.setBackgroundColor(swatch.getRgb());
                        break;
                    }
                }
            }
        });
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(itemListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        public ImageView head, picture;

        public TextView title, content;

        public MyHolder(View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.main_picture);
            head = (ImageView) itemView.findViewById(R.id.main_head);
            title = (TextView) itemView.findViewById(R.id.main_title);
            content = (TextView) itemView.findViewById(R.id.main_content);
        }
    }
}
