package com.wzq.material.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.wzq.material.R;

/**
 * Created by wzq on 15/6/30.
 */
public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBodyView(R.layout.activity_detail);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ViewCompat.setTransitionName(imageView, "image");
        imageView.setImageResource(MainActivity.pictures[getIntent().getIntExtra("url", 0)]);
        baseLoading.setVisibility(View.GONE);
    }
}
