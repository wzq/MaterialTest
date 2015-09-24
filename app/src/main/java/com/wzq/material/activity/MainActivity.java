package com.wzq.material.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.wzq.EasyAdapter;
import com.github.wzq.utils.DividerItemDecoration;
import com.github.wzq.utils.EasyMap;
import com.wzq.material.R;
import com.wzq.material.fragment.ScaleUpFragment;
import com.wzq.material.fragment.TransitionFragment;
import com.wzq.material.fragment.TransitionListFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener, EasyAdapter.CallBack{

    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;

    private RecyclerView drawerList;

    public static int[] pictures = {R.drawable.image_category_entertainment, R.drawable.image_category_food,
            R.drawable.image_category_geography, R.drawable.image_category_music,
            R.drawable.image_category_science, R.drawable.image_category_knowledge,
            R.drawable.image_category_tvmovies, R.drawable.image_category_history,
            R.drawable.image_category_sports};

    public static int[] logos = {R.drawable.icon_category_entertainment, R.drawable.icon_category_food,
            R.drawable.icon_category_geography, R.drawable.icon_category_music,
            R.drawable.icon_category_science, R.drawable.icon_category_knowledge,
            R.drawable.icon_category_tvmovies, R.drawable.icon_category_history,
            R.drawable.icon_category_sports};

    public static String[] types = {"Transition", "Transition List", "ScaleUp"};

    public static Class<?>[] fragments = {TransitionFragment.class, TransitionListFragment.class, ScaleUpFragment.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        drawerList = (RecyclerView) findViewById(R.id.main_drawer_list);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        drawerList.setLayoutManager(new LinearLayoutManager(this));
        drawerList.addItemDecoration(new DividerItemDecoration(this, R.drawable.div_normal));

        initDrawerList();
        selectFragment(0);
    }

    private void initDrawerList(){
        List<Object> data = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            EasyMap temp = new EasyMap();
            temp.put("content",types[i]);
            data.add(temp);
        }
        EasyAdapter adapter = new EasyAdapter(data, R.layout.item_drawer, new int[]{R.id.drawer_content}, this);
        drawerList.setAdapter(adapter);
    }

    public void selectFragment(int index){
        try {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, (Fragment) fragments[index].newInstance()).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        int index = (int) view.getTag();
        selectFragment(index);
        drawerLayout.closeDrawers();
    }

    @Override
    public void bindItemView(EasyAdapter.EasyHolder holder, Object item, int position) {
        EasyMap map = (EasyMap) item;
        holder.textViews[0].setText(map.getString("content"));
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }
}
