package com.example.dell.woof.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;

import com.example.dell.woof.R;

public class OtpValidationActivity extends BaseActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "BaseActivity";
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;
    private EditText etOtp;

    private BroadcastReceiver smsListener = new BroadcastReceiver() {

        private SharedPreferences preferences;
        public static final String SMS_ORIGIN = "Mmessage";
        // special character to prefix the otp. Make sure this character appears only once in the sms
        public static final String OTP_DELIMITER = "OTP";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    try {
                        Object[] pdusObj = (Object[]) bundle.get("pdus");
                        for (Object aPdusObj : pdusObj) {
                            SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);
                            String senderAddress = currentMessage.getDisplayOriginatingAddress();
                            String message = currentMessage.getDisplayMessageBody();
                            if (senderAddress.toLowerCase().contains(SMS_ORIGIN.toLowerCase())) {
                                if (!getVerificationCode(message).isEmpty()){
                                    hideProgressDialog();
                                    etOtp.setText(getVerificationCode
                                            (message));
                                    gotoDashBoardOnConfirm();
                                }
                            } else
                                return;
                        }
                    } catch (Exception e) {
                        Log.d("Exception caught", e.getMessage());
                    }
                }
            }
        }

        private String getVerificationCode(String message) {
            String code = null;
            int index = message.indexOf(OTP_DELIMITER);

            if (index != -1) {
                int start = index + 7;
                int length = 4;
                code = message.substring(start, start + length);
                return code;
            }

            return code;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_validation_screen);
        etOtp = (EditText) findViewById(R.id.etOtp);
     //   registerBroadcastReceiver();
        showProgressDialog("Waiting for otp...");



        final Handler handler = new Handler();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        ((EditText) findViewById(R.id.etOtp)).setText("436728");
                    }
                }, 3000);
            }
        });



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(OtpValidationActivity.this, DogProfileActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
        });
    }

    private void gotoDashBoardOnConfirm(){

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    @Override
//    protected void onPause() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
//        isReceiverRegistered = false;
//        super.onPause();
//    }
//
//    public void registerBroadcastReceiver() {
//        this.registerReceiver(smsListener, new IntentFilter(
//                "android.provider.Telephony.SMS_RECEIVED"));
//    }
//
//    public void unregisterBroadcastReceiver() {
//        this.unregisterReceiver(smsListener);
//    }
//
//    @Override
//    protected void onDestroy() {
//        unregisterBroadcastReceiver();
//        Runtime.getRuntime().gc();
//        super.onDestroy();
//    }
}
