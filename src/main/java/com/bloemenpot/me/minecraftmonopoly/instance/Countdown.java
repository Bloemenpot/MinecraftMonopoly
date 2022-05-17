package com.bloemenpot.me.minecraftmonopoly.instance;

import com.bloemenpot.me.minecraftmonopoly.GameState;
import com.bloemenpot.me.minecraftmonopoly.MinecraftMonopoly;
import com.bloemenpot.me.minecraftmonopoly.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private MinecraftMonopoly main;
    private Arena arena;
    private int countdownSeconds;


    public Countdown(MinecraftMonopoly main, Arena arena) {
        this.main = main;
        this.arena = arena;
        this.countdownSeconds = ConfigManager.getCountdownSeconds();
    }

    public void start() {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(main, 0, 20);
    }

    @Override
    public void run() {
        if (countdownSeconds == 0) {
            cancel();
            //start the arena
            arena.start();
            return;
        }

        if (countdownSeconds <= 10 || countdownSeconds % 15 == 0){
            arena.sendMessage(ChatColor.GREEN + "Game will start in " + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s") + ".");
        }

        countdownSeconds--;
    }
}
