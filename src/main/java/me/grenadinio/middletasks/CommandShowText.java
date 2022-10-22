package me.grenadinio.middletasks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandShowText implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }

        int fadeIn = 10; //Время появления надписи - ticks
        int stay = 70; //Время жизни надписи - ticks
        int fadeOut = 20; //Время затухания надписи - ticks


        if (args.length == 0) {
            sender.sendMessage("Missing argument. Usage: /showtext <text1> [text2]");
        } else if (args.length > 2) {
            sender.sendMessage("Too many arguments. Usage: /showtext <text1> [text2]");
        } else {
            if(args.length == 1){
                ((Player) sender).sendTitle(args[0],null,fadeIn,stay,fadeOut);
            }else{
                ((Player) sender).sendTitle(args[0],args[1],fadeIn,stay,fadeOut);
            }

        }

        return true;
    }
}
