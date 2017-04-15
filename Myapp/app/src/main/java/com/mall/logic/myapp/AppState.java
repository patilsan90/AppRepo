package com.mall.logic.myapp;

import com.mall.logic.myapp.home.mycart.CartItem;
import com.mall.logic.myapp.login.LoginActivity;

import java.util.ArrayList;

/**
 * Created by kapil on 14/4/17.
 */

public class AppState {

public static enum LOGIN_STATE {LOGIN_VIEW, REGISTRATION_VIEW};

    public LOGIN_STATE loginState;

    public LoginActivity loginActivity;

    private ArrayList<CartItem> cartItemsList;

    private static AppState state;

    private AppState()
    {
        cartItemsList = new ArrayList<CartItem>();
    }

    public static AppState getInstance()
    {
        if(state == null)
         state = new AppState();

        return state;
    }
    public void addCartItem(CartItem cartItem)
    {
        cartItemsList.add(cartItem);

    }
    public ArrayList<CartItem> getCartItemList()
    {
        return cartItemsList;
    }

}
