package me.grenadinio.middletasks.commands;

import me.grenadinio.middletasks.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class CommandFly implements CommandExecutor {
    private final Main plugin;

    public CommandFly(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }
        Player player = (Player) sender;


        if (args.length == 0) {
            boolean enabled = player.getPersistentDataContainer().has(plugin.FLY_KEY, PersistentDataType.INTEGER);
            if (!enabled) {
                player.getPersistentDataContainer().set(plugin.FLY_KEY, PersistentDataType.INTEGER, 1);
                player.setAllowFlight(true);
                player.sendMessage(String.format("Игроку %s включен полёт.", player.getName()));
            } else {
                player.getPersistentDataContainer().remove(plugin.FLY_KEY);
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(String.format("Игроку %s выключен полёт.", player.getName()));
            }
        } else if (args.length == 1) {
            Player secondPlayer = Bukkit.getServer().getPlayer(args[0]);
            if (secondPlayer != null) {
                boolean enabled = secondPlayer.getPersistentDataContainer().has(plugin.FLY_KEY, PersistentDataType.INTEGER);
                if (!enabled) {
                    secondPlayer.getPersistentDataContainer().set(plugin.FLY_KEY, PersistentDataType.INTEGER, 1);
                    secondPlayer.setAllowFlight(true);
                    player.sendMessage(String.format("Игроку %s включен полёт.", secondPlayer.getName()));
                } else {
                    secondPlayer.getPersistentDataContainer().remove(plugin.FLY_KEY);
                    secondPlayer.setAllowFlight(false);
                    secondPlayer.setFlying(false);
                    player.sendMessage(String.format("Игроку %s выключен полёт.", secondPlayer.getName()));
                }
            } else {
                player.sendMessage("Неверно введён ник или игрок не в сети.");
            }
        } else {
            player.sendMessage("Неверно введена команда. Использование: /fly [Ник]");
        }
        return true;
    }
}
