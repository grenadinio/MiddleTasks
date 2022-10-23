package me.grenadinio.middletasks.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CommandGm implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            sender.sendMessage("Missing argument. Usage: /gm <0-3>");
        } else if (args.length > 1) {
            sender.sendMessage("Too many arguments. Usage: /gm <0-3>");
        } else {
            if (Objects.equals(args[0], "0")) {
                player.setGameMode(GameMode.SURVIVAL);
                sender.sendMessage(String.format("Игроку %s установлен режим SURVIVAL.", player.getName()));
            } else if (Objects.equals(args[0], "1")) {
                player.setGameMode(GameMode.CREATIVE);
                sender.sendMessage(String.format("Игроку %s установлен режим CREATIVE.", player.getName()));
            } else if (Objects.equals(args[0], "2")) {
                player.setGameMode(GameMode.ADVENTURE);
                sender.sendMessage(String.format("Игроку %s установлен режим ADVENTURE.", player.getName()));
            } else if (Objects.equals(args[0], "3")) {
                player.setGameMode(GameMode.SPECTATOR);
                sender.sendMessage(String.format("Игроку %s установлен режим SPECTATOR.", player.getName()));
            } else {
                sender.sendMessage("Wrong argument. Usage: /gm <0-3>");
            }
        }

        return true;
    }
}
