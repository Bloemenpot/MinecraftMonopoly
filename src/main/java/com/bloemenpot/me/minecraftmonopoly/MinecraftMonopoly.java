package com.bloemenpot.me.minecraftmonopoly;

import com.bloemenpot.me.minecraftmonopoly.command.ArenaCommand;
import com.bloemenpot.me.minecraftmonopoly.listener.ConnectListener;
import com.bloemenpot.me.minecraftmonopoly.listener.GameListener;
import com.bloemenpot.me.minecraftmonopoly.manager.ConfigManager;
import com.bloemenpot.me.minecraftmonopoly.manager.MapManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftMonopoly extends JavaPlugin {

    private MapManager mapManager;

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);
        mapManager = new MapManager(this);

        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
        getCommand("arena").setExecutor(new ArenaCommand(this));
    }

    public MapManager getMapManager() { return mapManager; }

}
