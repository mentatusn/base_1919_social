package com.gb.base_1919_social.repository;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.gb.base_1919_social.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LocalSharedPreferencesRepositoryImpl implements PostsSource{
    private List<PostData> dataSource;
    private SharedPreferences sharedPreferences;

    static final String KEY_CELL_1 = "cell_2";
    public static final String KEY_SP_2 = "key_sp_2";

    public LocalSharedPreferencesRepositoryImpl(SharedPreferences sharedPreferences){
        dataSource = new ArrayList<PostData>();
        this.sharedPreferences = sharedPreferences;
    }

    public LocalSharedPreferencesRepositoryImpl init(){
        String savedPost= sharedPreferences.getString(KEY_CELL_1,null);
        if(savedPost!=null){
            Type type = new TypeToken<ArrayList<PostData>>(){}.getType();
            dataSource = (new GsonBuilder().create().fromJson(savedPost,type));
        }
        return this;
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public List<PostData> getAllCardsData() {
        return dataSource;
    }

    @Override
    public PostData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public void clearCardsData() {
        dataSource.clear();
        // HW
    }

    @Override
    public void addCardData(PostData postData) {
        dataSource.add(postData);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CELL_1,new GsonBuilder().create().toJson(dataSource));
        editor.apply();
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
        // HW
    }

    @Override
    public void updateCardData(int position, PostData newPostData) {
        dataSource.set(position, newPostData);
        // HW
    }
}
