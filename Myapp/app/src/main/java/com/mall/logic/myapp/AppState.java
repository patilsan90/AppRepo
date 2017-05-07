package com.mall.logic.myapp;

import com.google.android.gms.common.api.GoogleApiClient;
import com.mall.logic.myapp.home.mycart.CartItem;
import com.mall.logic.myapp.home.offers.OfferItem;
import com.mall.logic.myapp.login.LoginActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kapil on 14/4/17.
 */

public class AppState {

public static enum LOGIN_STATE {LOGIN_VIEW, REGISTRATION_VIEW};

    public LOGIN_STATE loginState;

    public LoginActivity loginActivity;

    private ArrayList<CartItem> cartItemsList;
    private ArrayList<OfferItem> offersList;

    public static boolean isMallSelected = false;

    private static AppState state;

    public static String sessionFile;
    public static String AppCacheFolder;

    public static boolean isProductScan;

    private AppState()
    {
        cartItemsList = new ArrayList<CartItem>();
        offersList = new ArrayList<OfferItem>();
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

    public void addOfferItem(OfferItem cartItem)
    {
        offersList.add(cartItem);
    }
    public ArrayList<OfferItem> getOfferItemList()
    {
        return offersList;
    }

    public static boolean checkProccedStatus()
    {
        if(isMallSelected == false)
            return false;
        return true;
    }


}
