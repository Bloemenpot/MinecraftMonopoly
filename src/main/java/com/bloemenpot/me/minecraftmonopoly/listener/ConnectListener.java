package com.bloemenpot.me.minecraftmonopoly.listener;

import com.bloemenpot.me.minecraftmonopoly.MinecraftMonopoly;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectListener implements Listener {

    private MinecraftMonopoly main;

    public ConnectListener(MinecraftMonopoly main){
        this.main = main;
    }

    @EventHandler
    public void OnConnect(PlayerJoinEvent e){

    }
}
