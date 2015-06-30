package com.wzq.material.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wzq.material.R;
import com.wzq.material.adapter.MyAdapter;
import com.wzq.material.util.EasyMap;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener{

    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;

    private RecyclerView recyclerView;

    private List<EasyMap> data = new ArrayList<>();

    public static int[] pictures = {R.drawable.image_category_entertainment, R.drawable.image_category_food,
            R.drawable.image_category_geography, R.drawable.image_category_music,
            R.drawable.image_category_science, R.drawable.image_category_knowledge,
            R.drawable.image_category_tvmovies, R.drawable.image_category_history,
            R.drawable.image_category_sports};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < pictures.length; i++) {
            EasyMap temp = new EasyMap();
            temp.put("title", "Great Title " + i);
            temp.put("content", "The test content of number " + i);
            temp.put("picture", pictures[i]);
            data.add(temp);
        }
        recyclerView.setAdapter(new MyAdapter(this, data, this));
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view.findViewById(R.id.main_picture), "image");
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("url", Integer.valueOf(view.getTag().toString()));
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}
