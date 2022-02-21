package com.gb.base_1919_social.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gb.base_1919_social.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,SocialNetworkFragment.newInstance()).commit();
        }
    }
}