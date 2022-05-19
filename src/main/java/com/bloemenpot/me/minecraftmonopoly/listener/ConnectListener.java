package com.bloemenpot.me.minecraftmonopoly.listener;

import com.bloemenpot.me.minecraftmonopoly.MinecraftMonopoly;
import com.bloemenpot.me.minecraftmonopoly.instance.Arena;
import com.bloemenpot.me.minecraftmonopoly.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener implements Listener {

    private MinecraftMonopoly main;

    public ConnectListener(MinecraftMonopoly main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().teleport(ConfigManager.getLobbySpawn());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        Arena arena = main.getMapManager().getArena(e.getPlayer());
        if (arena != null) {
            arena.removePlayer(e.getPlayer());
        }

    }
}
