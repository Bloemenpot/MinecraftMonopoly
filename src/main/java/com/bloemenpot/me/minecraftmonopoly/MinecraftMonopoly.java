package com.bloemenpot.me.minecraftmonopoly;

import com.bloemenpot.me.minecraftmonopoly.manager.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftMonopoly extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
