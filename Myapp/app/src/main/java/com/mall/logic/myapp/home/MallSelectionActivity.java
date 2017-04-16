package com.mall.logic.myapp.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mall.logic.myapp.AppState;
import com.mall.logic.myapp.R;

public class MallSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_selection);
        LinearLayout camLayout = (LinearLayout)findViewById(R.id.scan_qr_camera);
        int camWidth = camLayout.getWidth();
        camLayout.setLayoutParams(new LinearLayout.LayoutParams(camWidth, camWidth));
    }

void selectMall(View view)
{
//if(authentication true)
    {
        AppState.isMallSelected = true;
        this.finish();
    }
}


}
