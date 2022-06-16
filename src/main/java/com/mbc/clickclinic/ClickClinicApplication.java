package com.mbc.clickclinic;

import com.mbc.clickclinic.security.SecurityConfig;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ClickClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickClinicApplication.class, args);
    }

    @Bean
    public Bot getBot(@Value(value = "/Users/Haytam/OneDrive/Bureau/Haytam/Programming Practice/Spring practice/Spring guestbook app/scrinium/Project-Scrinium") String path,
                      @Value(value = "simplebot") String botname) {
        Bot bot = new Bot(botname, path);
        return bot;

    }

    @Bean
    public Chat getChat(Bot bot) {
        Chat chatSession = new Chat(bot);
        return chatSession;

    }

}
