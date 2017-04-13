package com.mall.logic.myapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mall.logic.myapp.fragments.MyCart;
import com.mall.logic.myapp.fragments.MyOrdersHistory;
import com.mall.logic.myapp.fragments.Offers;
import com.mall.logic.myapp.fragments.ScanProduct;

public class MainActivity extends AppCompatActivity {

    public MyCart myCart;
    public ScanProduct scanProduct;
    public Offers offers;
    private boolean isBack=false;
    private Button showCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        showCartButton= (Button) findViewById(R.id.showCart);
        scanProduct = new ScanProduct();
        myCart = new MyCart();
        offers =new Offers();

        fragmentTransaction.add(R.id.home_fragment, offers);
        fragmentTransaction.commit();
    }

    public void scanProduct(View view)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();


        fragmentTransaction.replace(R.id.home_fragment, scanProduct);
        fragmentTransaction.commit();
    }

   public void showMyCart(View view)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();


        if(isBack == false)
        {
            showCartButton.setText("Back");
            fragmentTransaction.replace(R.id.home_fragment, myCart);
            isBack = true;
        }
        else
        {
            showCartButton.setText("My Cart");
            fragmentTransaction.replace(R.id.home_fragment, offers);
            isBack = false;
        }

        fragmentTransaction.commit();
    }

}
