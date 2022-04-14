package com.mbc.clickclinic;

import com.mbc.clickclinic.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ClickClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickClinicApplication.class, args);
    }

}
