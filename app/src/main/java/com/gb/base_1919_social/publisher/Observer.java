package com.gb.base_1919_social.publisher;

import com.gb.base_1919_social.repository.PostData;

public interface Observer {
    void receiveMessage(PostData postData);
}
