package com.example.dell.woof.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dell.woof.R;
import com.example.dell.woof.WoofApplication;
import com.example.dell.woof.model.UserDetails;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private static int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeAll();
    }

    private void initializeAll() {
        findViewById(R.id.btnFacebook).setOnClickListener(this);
        findViewById(R.id.btnGoogle).setOnClickListener(this);
        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.ll1).setOnClickListener(this);
        facebookSignIn();
        googleSignIn();
    }

    private void facebookSignIn() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                showProgressDialog("Logging In");
                final UserDetails currentUser = new UserDetails();
                Profile currentProfile = Profile.getCurrentProfile();
                if (currentProfile != null){
                    currentUser.setUserName(currentProfile.getName());
                    currentUser.setUserProfileImage(currentProfile.getProfilePictureUri(400, 400).toString());
                    currentUser.setUserID(currentProfile.getId());
                }
                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    hideProgressDialog();
                                    currentUser.setUserEmail(object.getString("email"));
                                    WoofApplication.getWoofApplication().initializeUser(currentUser);
                                    Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                                    startActivity(intent);
                                    finish();
                                } catch (JSONException e) {
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields",
                        "id,name,email,gender, birthday,friends,albums");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "User cancelled login with facebook", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void googleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFacebook :
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends"));
                break;

            case R.id.ll1 :
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.btnSignIn :
                if (((EditText) findViewById(R.id.etEmail)).getEditableText().toString().equals("")){
                    findViewById(R.id.etEmail).requestFocus();
                    ((EditText) findViewById(R.id.etEmail)).setError("Please enter your email!");
                }
                else if (((EditText) findViewById(R.id.etPassword)).getEditableText().toString().equals("")){
                    findViewById(R.id.etPassword).requestFocus();
                    ((EditText) findViewById(R.id.etPassword)).setError("Please enter your password!");
                }
                else {
                    showProgressDialog("Logging In...");
                    HashMap<String, String> rawParams = new HashMap<String, String>();
                    rawParams.put("email", ((EditText) findViewById(R.id.etEmail)).getEditableText().toString());
                    rawParams.put("password", ((EditText) findViewById(R.id.etPassword)).getEditableText().toString());

                    com.android.volley.Response.Listener<UserDetails> listener = new Response.Listener<UserDetails>() {
                        @Override
                        public void onResponse(UserDetails response) {
                            hideProgressDialog();
                            WoofApplication.getWoofApplication().initializeUser(response);
                            Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    };

                    com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    };

                    BaseRequestClass.userLogin(LoginActivity.this, rawParams, listener, errorListener);
                }
                break;
            case R.id.btnGoogle :
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        } else if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            UserDetails currentUser = new UserDetails();
            currentUser.setUserName(account.getFamilyName());
            currentUser.setUserEmail(account.getEmail());
            currentUser.setUserProfileImage(account.getPhotoUrl().toString());
            WoofApplication.getWoofApplication().initializeUser(currentUser);
            Intent intent = new Intent(LoginActivity.this, CompleteUserDetails.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Problem in login with google", Toast.LENGTH_SHORT).show();
        }
    }
    //String profile_name=object.getString("name");
    //long fb_id=object.getLong("id"); //use this for logout
    //Start new activity or use this info in your project.
}
