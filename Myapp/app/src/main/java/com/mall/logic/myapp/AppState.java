package com.mall.logic.myapp;

import com.mall.logic.myapp.login.LoginActivity;

/**
 * Created by kapil on 14/4/17.
 */

public class AppState {

public  static enum LOGIN_STATE {LOGIN_VIEW, REGISTRATION_VIEW};

    public LOGIN_STATE loginState;
    public static LoginActivity loginActivity;

    private static AppState state;

    private AppState()
    {
    }

    public static AppState getInstance()
    {
        if(state == null)
         state = new AppState();

        return state;
    }

}
