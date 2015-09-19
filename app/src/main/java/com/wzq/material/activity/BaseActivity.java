package com.wzq.material.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.wzq.material.R;

/**
 * Created by wzq on 15/6/1.
 */
public class BaseActivity extends AppCompatActivity{

    protected Toolbar toolbar;

    protected ProgressBar baseLoading;

    protected FrameLayout baseBody;

    /**
     * If you have to custom a toolbar, you should use 'setContentView'.
     * @param resId
     */
    protected void setBodyView(int resId) {
        LinearLayout baseView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        baseBody = (FrameLayout) baseView.findViewById(R.id.base_body);
        baseBody.addView(getLayoutInflater().inflate(resId, null));
        setContentView(baseView);
        toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        baseLoading = (ProgressBar) findViewById(R.id.base_progress);
        setSupportActionBar(toolbar);
    }

    protected void setBodyView(View view) {
        LinearLayout baseView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        baseBody = (FrameLayout) baseView.findViewById(R.id.base_body);
        baseBody.addView(view);
        setContentView(baseView);
        toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        baseLoading = (ProgressBar) findViewById(R.id.base_progress);
        setSupportActionBar(toolbar);
    }

    protected void setBodyViewWithoutBar(int resId){
        LinearLayout baseView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        baseBody = (FrameLayout) baseView.findViewById(R.id.base_body);
        baseBody.addView(getLayoutInflater().inflate(resId, null));
        setContentView(baseView);
        findViewById(R.id.base_toolbar).setVisibility(View.GONE);
        baseLoading = (ProgressBar) findViewById(R.id.base_progress);
    }

}
