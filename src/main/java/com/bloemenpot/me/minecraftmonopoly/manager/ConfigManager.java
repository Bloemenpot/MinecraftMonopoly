package com.bloemenpot.me.minecraftmonopoly.manager;

import com.bloemenpot.me.minecraftmonopoly.MinecraftMonopoly;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setupConfig(MinecraftMonopoly main) {
        ConfigManager.config = main.getConfig();
        main.saveDefaultConfig();
    }

    public static int getMinimumPlayers() { return config.getInt("minimum-players"); }

    public static int getCountdownSeconds() { return config.getInt("countdown-seconds"); }

    public static Location getLobbySpawn() {
        return new Location(
                Bukkit.getWorld(config.getString("lobby-spawn.world")),
                config.getDouble("lobby-spawn.x"),
                config.getDouble("lobby-spawn.y"),
                config.getDouble("lobby-spawn.z"),
                (float) config.getDouble("lobby-spawn.yaw"),
                (float) config.getDouble("lobby-spawn.pitch")
        );
    }



    /* MONOPOLY */
    public static int getStartMoney() { return config.getInt("start-money"); }

}
