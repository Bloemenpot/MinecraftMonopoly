package com.bloemenpot.me.minecraftmonopoly.command;

import com.bloemenpot.me.minecraftmonopoly.GameState;
import com.bloemenpot.me.minecraftmonopoly.MinecraftMonopoly;
import com.bloemenpot.me.minecraftmonopoly.instance.Arena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    private MinecraftMonopoly main;

    public ArenaCommand(MinecraftMonopoly main){
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1 && args[0].equalsIgnoreCase("list")){
                player.sendMessage(ChatColor.GREEN + "These are the available arenas:");
                for (Arena arena : main.getMapManager().getArenas()) {
                    player.sendMessage(ChatColor.GREEN + "- " + arena.getId() + " (" + arena.getState().name() + ")");
                }


            } else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
                Arena arena = main.getMapManager().getArena(player);
                if (arena != null){
                    player.sendMessage(ChatColor.RED + "You left the arena.");
                    arena.removePlayer(player);
                } else {
                    player.sendMessage(ChatColor.RED + "You are not in an arena.");
                }


            } else if (args.length == 1 && args[0].equalsIgnoreCase("join")) {
                if (main.getMapManager().getArena(player) != null) {
                    player.sendMessage(ChatColor.RED + "You are already playing in an arena!");
                    player.sendMessage(ChatColor.RED + "Please use (/arena leave) first if you want to join a different arena.");
                    return false;
                }

                int id;
                try {
                    id = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "You specified an invalid arena ID.");
                    return false;
                }

                if (id >= 0 && id < main.getMapManager().getArenas().size()){
                    Arena arena = main.getMapManager().getArena(id);
                    if (arena.getState() == GameState.RECRUITING || arena.getState() == GameState.COUNTDOWN) {
                        player.sendMessage(ChatColor.GREEN + "You are now playing in Arena" + id + ".");
                        arena.addPlayer(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "The game you are trying to join is currently in progress!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You specified an invalid arena ID.");
                }

            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! These are the options:");
                player.sendMessage(ChatColor.RED + "- /arena list");
                player.sendMessage(ChatColor.RED + "- /arena leave");
                player.sendMessage(ChatColor.RED + "- /arena join <id>");
            }
        }

        return false;
    }
}
