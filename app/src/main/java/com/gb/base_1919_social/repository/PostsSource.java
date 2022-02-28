package com.gb.base_1919_social.repository;

import java.util.List;

public interface PostsSource {
    int size();
    List<PostData> getAllCardsData();
    PostData getCardData(int position);

    void clearCardsData();
    void addCardData(PostData postData);

    void deleteCardData(int position);
    void updateCardData(int position, PostData newPostData);
}
