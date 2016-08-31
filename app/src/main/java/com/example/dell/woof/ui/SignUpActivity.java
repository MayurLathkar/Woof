package com.example.dell.woof.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dell.woof.R;
import com.example.dell.woof.model.UserDetails;

import java.util.HashMap;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.ll1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog("Signing In...");
                HashMap<String, String> rawParams = new HashMap<String, String>();
                rawParams.put("name", ((EditText)findViewById(R.id.etName)).getEditableText().toString());
                rawParams.put("email", ((EditText)findViewById(R.id.etEmail)).getEditableText().toString());
                rawParams.put("password", ((EditText)findViewById(R.id.etPassword)).getEditableText().toString());
                rawParams.put("number", ((EditText)findViewById(R.id.etPhone)).getEditableText().toString());
                com.android.volley.Response.Listener<UserDetails> listener = new Response.Listener<UserDetails>() {
                    @Override
                    public void onResponse(UserDetails response) {
                        hideProgressDialog();
                        Toast.makeText(SignUpActivity.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, DogProfileActivity.class);
                        startActivity(intent);
                        finish();
                    }
                };

                com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, "Some thing went wrong!", Toast.LENGTH_SHORT).show();
                    }
                };

                BaseRequestClass.userSignUp(SignUpActivity.this, rawParams, listener, errorListener);
            }
        });
    }
}
