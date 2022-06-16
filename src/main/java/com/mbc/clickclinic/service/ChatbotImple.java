package com.mbc.clickclinic.service;

import org.alicebot.ab.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatbotImple implements ChatbotService{

    @Autowired
    private Chat chat;

    public String getChatResponse(String request){
        return chat.multisentenceRespond(request);
    }
}
