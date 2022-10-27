package me.grenadinio.middletasks.commands;

import me.grenadinio.middletasks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandAddNum implements CommandExecutor {
    public List<String> ConfigList;

    public CommandAddNum(Main plugin) {
        this.ConfigList = plugin.configList;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
        } else if (args.length != 1) {
            sender.sendMessage("Неверно введена команда. Использование: /addnum [число]");
        } else {
            ConfigList.add(args[0]);
            sender.sendMessage(String.format("Добавлено число: %s.", args[0]));
        }
        return true;
    }

//    public Integer getNumber() {
//        if (!list.isEmpty()) {
//            int number = list.get(0);
//            list.remove(0);
//            return number;
//        } else {
//            return null;
//        }
//    }
}
