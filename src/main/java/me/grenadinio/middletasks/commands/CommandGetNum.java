package me.grenadinio.middletasks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGetNum implements CommandExecutor {
    CommandAddNum num = new CommandAddNum();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
        } else if (args.length != 0) {
            sender.sendMessage("Неверно введена команда. Использование: /getnum");
        } else if (num.getNumber() == null) {
            sender.sendMessage("Вы не добавили числа. Используйте команду /addnum ");
        } else {
            sender.sendMessage(String.format("Число: %s", num.getNumber()));
        }
//        try{
//            sender.sendMessage(String.format("Число: %s", num.list.get(0)));
//        } catch (Exception e){
//            sender.sendMessage("Вы не добавили числа. Используйте команду /addnum ");
//        }
        return true;
    }
}
