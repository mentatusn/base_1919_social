package com.gb.base_1919_social.publisher;

import com.gb.base_1919_social.repository.CardData;

public interface Observer {
    void receiveMessage(CardData cardData);
}
