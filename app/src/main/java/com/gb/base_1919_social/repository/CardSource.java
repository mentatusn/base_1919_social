package com.gb.base_1919_social.repository;

import java.util.List;

public interface CardSource {
    int size();
    List<CardData> getAllCardsData();
    CardData getCardData(int position);
}
