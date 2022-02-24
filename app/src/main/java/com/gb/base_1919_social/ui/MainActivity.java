package com.gb.base_1919_social.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gb.base_1919_social.R;
import com.gb.base_1919_social.publisher.Publisher;
import com.gb.base_1919_social.ui.main.SocialNetworkFragment;

public class MainActivity extends AppCompatActivity {

    private Publisher publisher;
    private Navigation navigation;

    public Publisher getPublisher() {
        return publisher;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        publisher = new Publisher();
        navigation = new Navigation(getSupportFragmentManager());
        if(savedInstanceState==null){
            navigation.replaceFragment(SocialNetworkFragment.newInstance(),false);
        }
    }
}