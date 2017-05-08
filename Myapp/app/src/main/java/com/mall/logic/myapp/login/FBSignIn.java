package com.mall.logic.myapp.login;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.mall.logic.myapp.AppState;
import com.mall.logic.myapp.server_communication.UserAuthentication;

/**
 * Created by Sandeep Patil on 8/5/17.
 */

public class FBSignIn {

    public static final int RC_SIGN_IN = 64206;
    private static final String TAG = "CognitionMall";

    public FBSignIn(LoginActivity loginActivity) {
    }


}