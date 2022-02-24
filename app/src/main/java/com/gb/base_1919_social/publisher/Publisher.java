package com.gb.base_1919_social.publisher;

import com.gb.base_1919_social.repository.CardData;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Observer> observers;

    public Publisher(){
        observers = new ArrayList<>();
    }
    public void subscribe(Observer observer) {
        observers.add(observer);
    }
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void sendMessage(CardData cardData){
        for(Observer observer:observers){
            observer.receiveMessage(cardData);
        }
    }
}
