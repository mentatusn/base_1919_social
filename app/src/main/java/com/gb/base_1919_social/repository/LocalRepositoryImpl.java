package com.gb.base_1919_social.repository;

import android.content.res.Resources;
import android.content.res.TypedArray;

import com.gb.base_1919_social.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LocalRepositoryImpl implements CardsSource {

    private List<CardData> dataSource;
    private Resources resources;


    public LocalRepositoryImpl(Resources resources){
        dataSource = new ArrayList<CardData>();
        this.resources = resources;
    }

    public LocalRepositoryImpl init(){
        String[] titles = resources.getStringArray(R.array.titles);
        String[] descriptions = resources.getStringArray(R.array.descriptions);
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);

        for(int i=0;i<titles.length;i++){
            dataSource.add(new CardData(titles[i],descriptions[i],pictures.getResourceId(i,0),false, Calendar.getInstance().getTime()));
        }
        return this;
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public List<CardData> getAllCardsData() {
        return dataSource;
    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public void clearCardsData() {
        dataSource.clear();
    }

    @Override
    public void addCardData(CardData cardData) {
        dataSource.add(cardData);
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData newCardData) {
        dataSource.set(position,newCardData);
    }
}
