package com.wzq.material.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.wzq.EasyAdapter;
import com.github.wzq.utils.EasyMap;
import com.wzq.material.R;
import com.wzq.material.activity.DetailActivity;
import com.wzq.material.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzq on 15/6/30.
 */
public class ScaleUpFragment extends Fragment implements View.OnClickListener, EasyAdapter.CallBack{

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recycler_normal, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Object> data = new ArrayList<>();
        for (int i = 0; i < MainActivity.pictures.length; i++) {
            EasyMap temp = new EasyMap();
            temp.put("title", "Great Title " + i);
            temp.put("content", "The test content of number " + i);
            temp.put("picture", MainActivity.pictures[i]);
            data.add(temp);
        }
        int[] views = {R.id.main_picture, R.id.main_head, R.id.main_title, R.id.main_content};
        EasyAdapter adapter = new EasyAdapter(data, R.layout.item_main, views, this);
        adapter.setAnimId(R.anim.rotate_bottom_in);
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onClick(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth()/2, view.getHeight()/2, 0, 0);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("index", Integer.valueOf(view.getTag().toString()));
        intent.putExtra("title", "title "+view.getTag().toString());
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }

    @Override
    public void bindItemView(final EasyAdapter.EasyHolder holder, Object item, final int position) {
        EasyMap map = (EasyMap) item;
        holder.textViews.get(0).setText(map.getString("title"));
        holder.textViews.get(1).setText(map.getString("content"));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), map.getInt("picture", R.mipmap.ic_launcher));
        holder.imageViews.get(0).setImageBitmap(bitmap);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                if (palette.getVibrantSwatch() != null) {
                    holder.imageViews.get(0).setBackgroundColor(palette.getVibrantSwatch().getRgb());
                } else {
                    for (Palette.Swatch swatch : palette.getSwatches()) {
                        holder.imageViews.get(0).setBackgroundColor(swatch.getRgb());
                        break;
                    }
                }
            }
        });
        holder.itemView.setTag(position);
    }
}
