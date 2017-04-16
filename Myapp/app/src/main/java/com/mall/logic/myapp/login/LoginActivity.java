package com.mall.logic.myapp.login;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mall.logic.myapp.AppState;
import com.mall.logic.myapp.R;
import com.mall.logic.myapp.home.MainActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class LoginActivity extends AppCompatActivity {


    boolean isRegistrationForm = false;
    LoginFragment loginFragment;
    RegisterUserFragment registerUserFragment;

    EditText email;
    EditText mobNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppState.getInstance().loginActivity = this;
        AppState.AppCacheFolder = getCacheDir().getAbsolutePath() + File.pathSeparator + "MallAppInfo";
        AppState.sessionFile = AppState.AppCacheFolder + File.pathSeparator +"SessionInfo.txt";

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.login_fragment_place, loginFragment);
        fragmentTransaction.commit();

        File sessionFile = new File(AppState.sessionFile);

        if(sessionFile.exists())
            startMainActivity();
    }

    void startMainActivity()
    {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("name", "Sample name"); //Optional parameter pass parameters
        startActivity(myIntent);
        this.finish();

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

   public void signinByGmail(View view)
    {
        //if(authentication successfull)
        String email_id = "temp@temp.com";
        String mob_no = "9999999999";
        String user_name = "Temporary Man";

        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.emailID = email_id;
        sessionInfo.mobNo = mob_no;
        sessionInfo.userName =user_name;
        Log.i("SessionInfo","Creating session using GMAIL credentials");

        if(writeObject(sessionInfo))
            startMainActivity();

        Log.i("SessionInfo","Session created successfully");
    }


    public void signinByFB(View view)
    {
        //if(authentication successfull)
        String email_id = "temp@temp.com";
        String mob_no = "9999999999";
        String user_name = "Temporary Man";

        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.emailID = email_id;
        sessionInfo.mobNo = mob_no;
        sessionInfo.userName =user_name;
        Log.i("SessionInfo","Creating session using FB credentials");

        if(writeObject(sessionInfo))
            startMainActivity();

        Log.i("SessionInfo","Session created successfully");
    }


    boolean writeObject(SessionInfo info)
    {
        File projDir = new File(AppState.AppCacheFolder);
        if (!projDir.exists())
            projDir.mkdirs();

        File sessionFile = new File(AppState.sessionFile);

        Log.i("SessionInfo","File Location ::" + AppState.sessionFile);
        if(!sessionFile.exists())
        {
        Log.i("SessionInfo","File does not exist, creating one");
            try {
                sessionFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Log.i("SessionInfo","File exist yeyeye");

        }

        try (
                OutputStream file = new FileOutputStream(AppState.sessionFile);
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
        ){
            output.writeObject(info);
        }
        catch(IOException ex){
            //fLogger.log(Level.SEVERE, "Cannot perform output.", ex);
            Log.e("SessionInfo","Error in writting session object");

        }
return true;
    }
}
