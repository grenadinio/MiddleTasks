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

        if (args.length == 0) {
            sender.sendMessage("Missing argument. Usage: /showtext <text1> [text2]");
        } else if (args.length > 2) {
            sender.sendMessage("Too many arguments. Usage: /showtext <text1> [text2]");
        } else {
            if(args.length == 1){
                ((Player) sender).sendTitle(args[0],"",10,70,20);
            }else{
                ((Player) sender).sendTitle(args[0],args[1],10,70,20);
            }

        }

        return true;
    }
}
