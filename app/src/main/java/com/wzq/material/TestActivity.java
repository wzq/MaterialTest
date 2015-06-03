package com.wzq.material;

import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

/**
 * Created by wzq on 15/6/3.
 */
public class TestActivity extends BaseActivity implements View.OnClickListener{

    private TextView t, t1, t2, t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBodyView(R.layout.activity_test);
        t = (TextView) findViewById(R.id.textView);
        t1 = (TextView) findViewById(R.id.textView1);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TranslateAnimation animation = new TranslateAnimation();
    }
}
