package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;


    @GetMapping("/chatRoom")
    public String chatRoom(Model model){
        return "chatArea";
    }

    @RequestMapping("/getResponse")
    @ResponseBody
    public String getResponse(@RequestParam(value = "request") String userRequest, Model model){
        return chatbotService.getChatResponse(userRequest);
    }
}
