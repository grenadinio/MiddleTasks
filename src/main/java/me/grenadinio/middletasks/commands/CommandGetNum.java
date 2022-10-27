package me.grenadinio.middletasks.commands;

import me.grenadinio.middletasks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandGetNum implements CommandExecutor {
    public List<String> ConfigList;

    public CommandGetNum(Main plugin) {
        this.ConfigList = plugin.configList;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
        } else if (args.length != 0) {
            sender.sendMessage("Неверно введена команда. Использование: /getnum");
        } else if (ConfigList.isEmpty()) {
            sender.sendMessage("Вы не добавили числа. Используйте команду /addnum ");
        } else {
            String num = ConfigList.get(0);
            ConfigList.remove(0);
            sender.sendMessage(String.format("Число: %s", num));
        }
        return true;
    }
}
