package com.texttutti.infrastructure;

import org.jfugue.player.Player;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Player player() {
        return new Player();
    }
}
