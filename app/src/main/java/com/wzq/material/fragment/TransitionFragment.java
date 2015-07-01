package com.wzq.material.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzq.material.R;
import com.wzq.material.activity.DetailActivity;
import com.wzq.material.activity.MainActivity;
import com.wzq.material.adapter.MyAdapter;
import com.wzq.material.util.EasyMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzq on 15/6/30.
 */
public class TransitionFragment extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recycler_normal, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<EasyMap> data = new ArrayList<>();
        for (int i = 0; i < MainActivity.pictures.length; i++) {
            EasyMap temp = new EasyMap();
            temp.put("title", "Great Title " + i);
            temp.put("content", "The test content of number " + i);
            temp.put("picture", MainActivity.pictures[i]);
            data.add(temp);
        }
        recyclerView.setAdapter(new MyAdapter(getActivity(), data, this));
        return root;
    }

    @Override
    public void onClick(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view.findViewById(R.id.main_picture), "image");
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("index", Integer.valueOf(view.getTag().toString()));
        intent.putExtra("title", "title "+view.getTag().toString());
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }
}
