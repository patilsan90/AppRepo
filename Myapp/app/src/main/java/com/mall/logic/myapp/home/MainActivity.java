package com.mall.logic.myapp.home;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mall.logic.myapp.AppState;
import com.mall.logic.myapp.R;
import com.mall.logic.myapp.home.mycart.MyCartFragment;
import com.mall.logic.myapp.home.offers.OffersFragment;
import com.mall.logic.myapp.login.LoginActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public MyCartFragment myCartFragment;
    public ScanProduct scanProduct;
    public OffersFragment offersFragment;
    private boolean isBack = false;
    private Button showCartButton;
    private int back_press_counter = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        showCartButton = (Button) findViewById(R.id.showCart);
        scanProduct = new ScanProduct();
        myCartFragment = new MyCartFragment();
        offersFragment = new OffersFragment();
        Log.i("Generic info ", " Activity onCreate MainActivity");
        fragmentTransaction.add(R.id.home_fragment, offersFragment);
        fragmentTransaction.commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createDrawer();
    }

    private void createDrawer() {
        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d("Mall_Drawer", "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        /* mDrawerLayout.setDrawerListener(mDrawerToggle); check if any issue comes because of addDrawerListener. */
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void scanProduct(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.home_fragment, scanProduct);
        fragmentTransaction.commit();
    }

    public void showMyCart(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (isBack == false) {
            showCartButton.setText("Back");
            fragmentTransaction.replace(R.id.home_fragment, myCartFragment);
            isBack = true;
        } else {
            showCartButton.setText("My Cart");
            fragmentTransaction.replace(R.id.home_fragment, offersFragment);
            isBack = false;
        }

        fragmentTransaction.commit();
    }

    public void locateCategories(View view) {
        Log.i("Home", "locateCategories Clicked");
        Intent myIntent = new Intent(this, LocateCategories.class);
        this.startActivity(myIntent);

    }

    public void selectMall(View view) {
        Log.i("Home", "Select mall Clicked");
        Intent myIntent = new Intent(this, MallSelectionActivity.class);
        this.startActivity(myIntent);

    }

    @Override
    public void onBackPressed() {
        back_press_counter++;
        if (back_press_counter == 2) {
            this.finish();
            super.onBackPressed();
            return;
        }
        Toast.makeText(this, "Press again to close App", Toast.LENGTH_SHORT).show();
    }

    public void logout(View view) {
        Log.i("Home", "Logout clicked");

        File sessionFile = new File(AppState.sessionFile);
        if (sessionFile.exists()) {
            Log.i("Home", "Logout, file exists, deleting");
            sessionFile.delete();
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
            this.finish();
        } else {
            Log.i("Home", "Logout, file does not exists, its a error case");
        }
    }
}
