package com.bloemenpot.me.minecraftmonopoly.instance;

import com.bloemenpot.me.minecraftmonopoly.GameState;
import com.bloemenpot.me.minecraftmonopoly.MinecraftMonopoly;
import com.bloemenpot.me.minecraftmonopoly.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Game {

    //TODO when game is done: arena.reset(true);

    private Arena arena;
    private List<UUID> activePlayers;
    private HashMap<UUID, Integer> thrownDice;
    private HashMap<Integer, UUID> playerOrder;
    private List<UUID> spectators;
    private HashMap<UUID, Integer> money;

    public Game(Arena arena){
        this.arena = arena;
    }

    public void start(){
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.GREEN + "GAME HAS STARTED!");

        for (UUID uuid : arena.getPlayers()) {
            money.put(uuid, ConfigManager.getStartMoney());
            activePlayers.add(uuid);
        }

    }

    public int throwDice() {
        return (int)Math.floor(Math.random()*(6-1+1)+1);
    }

    public void addMoney(Player player, int amount){
        int playerMoney = money.get(player.getUniqueId()) + amount;
        player.sendMessage(ChatColor.GREEN + "Added €" + amount);
        money.replace(player.getUniqueId(), playerMoney);
    }

}
