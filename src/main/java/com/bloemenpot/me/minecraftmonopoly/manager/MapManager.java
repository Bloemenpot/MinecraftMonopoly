package com.bloemenpot.me.minecraftmonopoly.manager;

import com.bloemenpot.me.minecraftmonopoly.instance.Arena;
import com.bloemenpot.me.minecraftmonopoly.MinecraftMonopoly;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MapManager {

    private List<Arena> arenas = new ArrayList<>();

    public MapManager(MinecraftMonopoly main) {
        FileConfiguration config = main.getConfig();

        for (String str : config.getConfigurationSection("maps.").getKeys(false)) {
            arenas.add(new Arena(main, Integer.parseInt(str), new Location(
                    Bukkit.getWorld(config.getString("arenas." + str + ".world")),
                    config.getDouble("arenas." + str + ".x"),
                    config.getDouble("arenas." + str + ".y"),
                    config.getDouble("arenas." + str + ".z"),
                    (float) config.getDouble("arenas." + str + ".yaw"),
                    (float) config.getDouble("arenas." + str + ".pitch"))));
        }
    }

    public List<Arena> getArenas() { return arenas; }

    public Arena getArena(Player player) {
        for (Arena arena : arenas) {
            if (arena.getPlayers().contains(player.getUniqueId())) {
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(int id) {
        for (Arena arena : arenas) {
            if (arena.getId() == id) {
                return arena;
            }
        }
        return null;
    }

}
