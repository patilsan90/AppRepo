package com.mall.logic.myapp.home;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.mall.logic.myapp.AppState;
import com.mall.logic.myapp.R;
import com.mall.logic.myapp.barcode.BarcodeCaptureActivity;
import com.mall.logic.myapp.home.mycart.ItemCountSelectionActivity;
import com.mall.logic.myapp.home.mycart.MyCartFragment;
import com.mall.logic.myapp.home.offers.OffersFragment;
import com.mall.logic.myapp.login.LoginActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int BARCODE_READER_REQUEST_CODE = 1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public MyCartFragment myCartFragment;
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

        if(AppState.checkProccedStatus()==false)
        {
            Toast.makeText(this, "Please select mall first !!!", Toast.LENGTH_SHORT).show();
            return;
        }

        AppState.isProductScan = true;
        Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
        startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);
        /*
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.home_fragment, scanProduct);
        fragmentTransaction.commit();
        */

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Point[] p = barcode.cornerPoints;
                    //mResultTextView.setText(barcode.displayValue);
                    //} else
                    //  mResultTextView.setText(R.string.no_barcode_captured);

                    if (AppState.isProductScan == false) {
                        // This indicates was executed to select mall
                        AppState.isMallSelected = true;
                    } else {
                        //Else condition indicates, scan executed to select item but not mall
                        Intent intent = new Intent(getApplicationContext(), ItemCountSelectionActivity.class);
                        startActivity(intent);
                        /* TODO ::
                         * If ItemCountSelectionActivity is not required then
                         * add item to cart at this place itself
                         */
                    }

                } else Log.e(LOG_TAG, String.format(getString(R.string.barcode_error_format),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            } else super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void showMyCart(View view) {

        if(AppState.checkProccedStatus()==false)
        {
            Toast.makeText(this, "Please select mall first !!!", Toast.LENGTH_SHORT).show();
            return;
        }

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
        AppState.isProductScan = false;
        Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
        startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);

//        Intent myIntent = new Intent(this, MallSelectionActivity.class);
        //      this.startActivity(myIntent);

    }

    @Override
    public void onBackPressed() {
        back_press_counter++;
        if (back_press_counter == 2) {
            this.finish();
            back_press_counter = 0;
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
