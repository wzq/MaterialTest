package com.wzq.material;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by wzq on 15/6/1.
 */
public class BaseActivity extends AppCompatActivity{

    protected Toolbar toolbar;

    protected ProgressBar baseLoading;

    /**
     * If you have to custom a toolbar, you should use 'setContentView'.
     * @param resId
     */
    protected void setBodyView(int resId) {
        LinearLayout baseView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout baseBody = (FrameLayout) baseView.findViewById(R.id.base_body);
        baseBody.addView(getLayoutInflater().inflate(resId, null));
        setContentView(baseView);
        toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        baseLoading = (ProgressBar) findViewById(R.id.base_progress);
        setSupportActionBar(toolbar);
        //ButterKnife.inject(this);
    }

    protected void setBodyView(View view) {
        LinearLayout baseView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout baseBody = (FrameLayout) baseView.findViewById(R.id.base_body);
        baseBody.addView(view);
        setContentView(baseView);
        toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        baseLoading = (ProgressBar) findViewById(R.id.base_progress);
        setSupportActionBar(toolbar);
        //ButterKnife.inject(this);
    }


    public void showTips(String content){
      //  Snackbar.make(this.ge, content, Snackbar.LENGTH_LONG).show();
    }

}
