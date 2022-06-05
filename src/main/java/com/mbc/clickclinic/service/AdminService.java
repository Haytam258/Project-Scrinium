package com.mbc.clickclinic.service;

import java.util.HashMap;

public interface AdminService {

    public HashMap<Integer, Integer> getPayementPerMonth();
    public Integer getPaymentTotal();
    public Integer getPatientCount();
}
