package com.example.dell.woof.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dell.woof.R;
import com.example.dell.woof.adapters.DoctorsViewPagerAdapter;
import com.example.dell.woof.fragments.ExploreFragment;
import com.example.dell.woof.fragments.NearByFragment;
import com.example.dell.woof.views.NonSwipeableViewPager;

public class DoctorsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NonSwipeableViewPager viewPager;
    private SlidingTabLayout tabs;
    private DoctorsViewPagerAdapter adapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        initializeAll();
    }

    private void initializeAll(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        viewPager = (NonSwipeableViewPager) findViewById(R.id.viewPager);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);
        tabs.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.cardview_dark_background);
            }
        });
        setUpViewPager();
    }

    private void setUpViewPager() {

        Bundle bundle = new Bundle();

        if (getIntent().getExtras().getString("type").equals("Veterinary")){
            bundle.putString("type","Veterinary");
            bundle.putSerializable("doctors",getIntent().getExtras().getSerializable("doctors"));
            sendDataToFragments(bundle);
            getSupportActionBar().setTitle("Doctors");

        } else if (getIntent().getExtras().getString("type").equals("Spa")) {
            bundle.putString("type","Spa");
            sendDataToFragments(bundle);
            getSupportActionBar().setTitle("Spa");
        } else if (getIntent().getExtras().getString("type").equals("Kennel")) {
            bundle.putString("type","Kennel");
            sendDataToFragments(bundle);
            getSupportActionBar().setTitle("Kennel");
        } else if (getIntent().getExtras().getString("type").equals("Lovenest")) {
            bundle.putString("type","Lovenest");
            sendDataToFragments(bundle);
            getSupportActionBar().setTitle("Lovenest");
        } else if (getIntent().getExtras().getString("type").equals("Store")){
            bundle.putString("type","Store");
            sendDataToFragments(bundle);
            getSupportActionBar().setTitle("Store");
        }

        viewPager.setAdapter(adapter);
        tabs.setViewPager(viewPager);
    }

    private void sendDataToFragments( Bundle bundle ){
        NearByFragment nearByFragment = new NearByFragment();
        ExploreFragment exploreFragment = new ExploreFragment();
        adapter = new DoctorsViewPagerAdapter(getSupportFragmentManager());
        nearByFragment.setArguments(bundle);
        adapter.addFrag(nearByFragment, "NEARBY");
        exploreFragment.setArguments(bundle);
        adapter.addFrag(exploreFragment, "EXPLORE");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(DoctorsActivity.this, DashBoardActivity.class);
        startActivity(intent);
        finish();
        return true;
    }
}
