package com.mbc.clickclinic.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Message {

    private String message;
    private String status;
    private LocalDateTime dateTime;

}
