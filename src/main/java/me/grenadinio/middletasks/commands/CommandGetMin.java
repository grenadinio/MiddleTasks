package me.grenadinio.middletasks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.OptionalInt;

public class CommandGetMin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }

        try {
            OptionalInt min = Arrays.stream(args).mapToInt(Integer::parseInt).min();
            if (min.isPresent()) {
                sender.sendMessage(String.format("Минимальное значение: %s", min.getAsInt()));
            }
        } catch (Exception e) {
            sender.sendMessage("Неверно введена команда.");
        }

        return true;
    }
}
