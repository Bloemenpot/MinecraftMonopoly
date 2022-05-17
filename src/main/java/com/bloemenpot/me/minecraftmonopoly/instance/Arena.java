package com.bloemenpot.me.minecraftmonopoly.instance;

import com.bloemenpot.me.minecraftmonopoly.GameState;
import com.bloemenpot.me.minecraftmonopoly.MinecraftMonopoly;
import com.bloemenpot.me.minecraftmonopoly.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private MinecraftMonopoly main;

    private int id;
    private Location spawn;

    private GameState state;
    private List<UUID> players;
    private Countdown countdown;
    private Game game;


    public Arena(MinecraftMonopoly main, int id, Location spawn) {
        this.main = main;

        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(main, this);
        this.game = new Game(this);
    }

    /* GAME */

    public void start() { game.start(); }

    public void reset(boolean kickPlayers) {
        if (kickPlayers) {
            Location loc = ConfigManager.getLobbySpawn();
            for (UUID uuid : players) {
                Bukkit.getPlayer(uuid).teleport(loc);
            }
            players.clear();
        }

        state = GameState.RECRUITING;
        countdown.cancel();
        countdown = new Countdown(main, this);
        game = new Game(this);

    }

    /* TOOLS */

    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public void sendTitle(String title, String subtitle) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendTitle(title, subtitle);
        }
    }

    /* PLAYERS */

    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        if (state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getMinimumPlayers()){
            countdown.start();
        }
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
    }


    /* INFO */

    public int getId() { return id; }

    public GameState getState() { return state; }
    public List<UUID> getPlayers() { return players; }

    public void setState(GameState state) { this.state = state; }

}
