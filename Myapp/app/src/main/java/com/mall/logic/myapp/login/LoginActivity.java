package com.mall.logic.myapp.login;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mall.logic.myapp.AppState;
import com.mall.logic.myapp.R;


public class LoginActivity extends AppCompatActivity {


    boolean isRegistrationForm = false;
    LoginFragment loginFragment;
    RegisterUserFragment registerUserFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppState.getInstance().loginActivity = this;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.login_fragment_place, loginFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (AppState.getInstance().loginState == AppState.LOGIN_STATE.LOGIN_VIEW)
            super.onBackPressed();
        else {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.login_fragment_place, loginFragment);
            fragmentTransaction.commit();
            AppState.getInstance().loginState = AppState.LOGIN_STATE.LOGIN_VIEW;
        }


    }
}
