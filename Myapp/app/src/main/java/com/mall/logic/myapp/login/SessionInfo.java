package com.mall.logic.myapp.login;

import java.io.Serializable;

/**
 * Created by Sandeep Patil on 17/4/17.
 */

public class SessionInfo implements Serializable{
    enum LOGIN_OTIONS {FB, GMAIL, OTHER};
    String userName;
    String mobNo;
    String emailID;
    String password;
    LOGIN_OTIONS logged_in_by;
    String sessionID;

}
