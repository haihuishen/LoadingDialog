package com.shen.loadingdialog;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.shen.loadingdialog.View.SpotsDialog;


/**
 *
 *
 */
public class LoadingTestActivity extends Activity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loading_test);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.closeButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                new SpotsDialog(this).show();
                break;
            case R.id.button2:
                new SpotsDialog(this, R.style.Custom).show();
                break;
            case R.id.button3:
                new SpotsDialog(this, "Custom message").show();
                break;
            case R.id.closeButton:
                new SpotsDialog(this, "Custom message & style", R.style.Custom).show();
                break;
        }
    }
}
