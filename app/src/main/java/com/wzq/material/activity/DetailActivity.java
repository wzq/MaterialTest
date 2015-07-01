package com.wzq.material.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;

import com.wzq.material.R;

/**
 * Created by wzq on 15/6/30.
 */
public class DetailActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBodyView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = (ImageView) findViewById(R.id.imageView);
        ViewCompat.setTransitionName(imageView, "image");
        ViewCompat.setTransitionName(toolbar, "title");
        setTitle(getIntent().getStringExtra("title"));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), MainActivity.pictures[getIntent().getIntExtra("index", 0)]);
        imageView.setImageResource(MainActivity.pictures[getIntent().getIntExtra("index", 0)]);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                if (palette.getVibrantSwatch() != null) {
                    imageView.setBackgroundColor(palette.getVibrantSwatch().getRgb());
                } else {
                    for (Palette.Swatch swatch : palette.getSwatches()) {
                        imageView.setBackgroundColor(swatch.getRgb());
                        break;
                    }
                }
            }
        });
        baseLoading.setVisibility(View.GONE);
    }
}
