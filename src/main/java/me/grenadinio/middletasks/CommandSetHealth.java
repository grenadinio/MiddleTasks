package me.grenadinio.middletasks;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetHealth implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            sender.sendMessage("Missing argument. Usage: /sethealth amount");
        } else if (args.length > 1) {
            sender.sendMessage("Too many arguments. Usage: /sethealth amount");
        } else {
            try{
                if(Integer.parseInt(args[0]) <= 0
                        || Double.parseDouble(args[0]) >  player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()){
                    sender.sendMessage("Аргументом должно быть число от 0 до вашего максимального хп");
                } else{
                    player.setHealth(Integer.parseInt(args[0]));
                }
            } catch (NumberFormatException ex){
                sender.sendMessage("Аргументом должно быть число.");
            }

        }
        return true;
    }
}
