package com.bloemenpot.me.minecraftmonopoly.instance;

import com.bloemenpot.me.minecraftmonopoly.GameState;
import com.bloemenpot.me.minecraftmonopoly.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    //TODO when game is done: arena.reset();

    private Arena arena;
    private HashMap<UUID, Integer> money;

    public Game(Arena arena){
        this.arena = arena;
    }

    public void start(){
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.GREEN + "GAME HAS STARTED!");

        for (UUID uuid : arena.getPlayers()) {
            money.put(uuid, ConfigManager.getStartMoney());
        }
    }

    public void addMoney(Player player, int amount){
        int playerMoney = money.get(player.getUniqueId()) + amount;
        player.sendMessage(ChatColor.GREEN + "Added €" + amount);
        money.replace(player.getUniqueId(), playerMoney);
    }

}
