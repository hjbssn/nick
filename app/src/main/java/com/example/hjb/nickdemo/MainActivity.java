package com.example.hjb.nickdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display mDisplay = getWindowManager().getDefaultDisplay();

        //int W = mDisplay.get;

        //int H = mDisplay.getHeight();

        //Log.i("MainActivity", "Width = " + W);

        //Log.i("MainActivity", "Height = " + H);

        setContentView(R.layout.phone_register);
    }
}
