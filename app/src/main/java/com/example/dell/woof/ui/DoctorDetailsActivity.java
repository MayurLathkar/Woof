package com.example.dell.woof.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.model.DoctorsDetails;

public class DoctorDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        intializeView();
    }

    private void intializeView(){
        Bundle bundle = getIntent().getExtras().getBundle("doctorBundle");
        final DoctorsDetails doctorsDetails = (DoctorsDetails) bundle.getSerializable("doctor");
        ((TextView) findViewById(R.id.tvTitle)).setText(doctorsDetails.getDtrName());
        ((TextView) findViewById(R.id.tvAddress)).setText(doctorsDetails.getDtrAddress() + "\n"+doctorsDetails.getDtrNumber());
        ((ImageView) findViewById(R.id.dtrImage)).setImageURI(Uri.parse(doctorsDetails.getDtrImage()));
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.btnBookApt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorDetailsActivity.this, CalenderActivity.class);
                intent.putExtra("doctor", doctorsDetails.getDtrName());
                startActivity(intent);
                finish();
            }
        });
    }

}
