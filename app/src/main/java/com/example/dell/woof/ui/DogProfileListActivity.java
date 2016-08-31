package com.example.dell.woof.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.dell.woof.R;
import com.example.dell.woof.adapters.DogAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praful on 16/8/16.
 */
public class DogProfileListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    DogAdapter adapter;
    int width;
    public static List<Bitmap> arrayList = new ArrayList<>();
    ArrayList<Bitmap> bitmaps = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_profile_ist);
        bitmaps.add(0,arrayList.get(0));
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;

        layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.dog_recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DogAdapter(getApplicationContext(), bitmaps);
        recyclerView.setAdapter(adapter);

        final Button fab = (Button) findViewById(R.id.next);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DogProfileListActivity.this, DashBoardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
