package me.grenadinio.middletasks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandAddNum implements CommandExecutor {
    ArrayList<Integer> list = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
        } else if (args.length != 1) {
            sender.sendMessage("Неверно введена команда. Использование: /addnum [число]");
        } else {
            list.add(Integer.parseInt(args[0]));
            sender.sendMessage(String.format("Добавлено число: %s. Список: %s", args[0], list));
        }
        return true;
    }

    public Integer getNumber() {
        if (!list.isEmpty()) {
            int number = list.get(0);
            list.remove(0);
            return number;
        } else {
            return null;
        }
    }
}
