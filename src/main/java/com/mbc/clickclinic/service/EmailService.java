package com.mbc.clickclinic.service;

public interface EmailService {

    public void sendSimpleMessage(String to, String body, String subject);
}
