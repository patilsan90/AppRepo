package com.mall.logic.myapp.home.mycart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mall.logic.myapp.AppState;
import com.mall.logic.myapp.R;

public class ItemCountSelectionActivity extends AppCompatActivity {

    static int tempCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_count_selection);
    }
    public void itemSelected(View view)
    {
    //add item to cart here
        CartItem temporary_item = new CartItem("Item Id", "Example "+(++tempCount), 30, 25, 5);
        AppState.getInstance().addCartItem(temporary_item);

        this.finish();
    }

}
