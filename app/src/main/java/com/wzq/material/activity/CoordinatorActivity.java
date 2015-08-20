package com.wzq.material.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.wzq.material.R;

/**
 * Created by wzq on 15/8/19.
 */
public class CoordinatorActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        ViewCompat.setTransitionName(imageView, "image");
        ViewCompat.setTransitionName(toolbar, "title");

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra("title"));

        imageView.setImageResource(MainActivity.pictures[getIntent().getIntExtra("index", 0)]);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finishAfterTransition();
        return super.onOptionsItemSelected(item);
    }
}
