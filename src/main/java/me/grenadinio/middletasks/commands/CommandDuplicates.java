package me.grenadinio.middletasks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CommandDuplicates implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("Укажите аргументы.");
        } else {
            Set<Integer> duplicates = new HashSet<>();
            Set<Integer> uniques = new HashSet<>();
            Arrays.stream(args).mapToInt(Integer::parseInt).forEach((Integer) -> {
                if (!uniques.add(Integer)) {
                    duplicates.add(Integer);
                }
            });
            sender.sendMessage(String.format("Повторяющиеся: %s", duplicates));
        }
        return true;
    }
}
