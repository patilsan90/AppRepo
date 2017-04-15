package com.mall.logic.myapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mall.logic.myapp.AppState;
import com.mall.logic.myapp.home.MainActivity;
import com.mall.logic.myapp.R;

public class VerifyMobileNoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_mobile_no);
    }


public void submitOTP(View view)
{
    Log.i("Login","Submitting OTP");

    Intent myIntent = new Intent(this, MainActivity.class);
    myIntent.putExtra("isVerifySuccess", "YES"); //Optional parameters
    this.startActivity(myIntent);
    this.finish();
    AppState.getInstance().loginActivity.finish();
}

}
