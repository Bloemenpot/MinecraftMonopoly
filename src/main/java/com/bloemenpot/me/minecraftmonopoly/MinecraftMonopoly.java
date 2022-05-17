package com.bloemenpot.me.minecraftmonopoly;

import com.bloemenpot.me.minecraftmonopoly.manager.ConfigManager;
import com.bloemenpot.me.minecraftmonopoly.manager.MapManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftMonopoly extends JavaPlugin {

    private MapManager mapManager;

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);

        mapManager = new MapManager(this);
    }

    public MapManager getMapManager() { return mapManager; }

}
