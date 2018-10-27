package com.texttutti.service;

import org.jfugue.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {

    private final Player player;

    @Autowired
    public MusicPlayer(Player player) {
        this.player = player;
    }

    public void playSomething() {
        player.play("C D E F G A B");
    }
}
