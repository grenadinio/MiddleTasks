package me.grenadinio.middletasks.commands;

import me.grenadinio.middletasks.MongoConnect;
import org.bson.Document;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class CommandBTP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        World world = player.getWorld();

        if (args.length == 0) {
            sender.sendMessage("Missing argument. Usage: /btp gold | diamond");
        } else if (args.length > 1) {
            sender.sendMessage("Too many arguments. Usage: /btp gold | diamond");
        } else {
            if (Objects.equals(args[0], "gold")) {
                Document result = MongoConnect.execute((collection -> collection.find(and(
                        eq("uuid", uuid.toString()),
                        eq("type", Material.GOLD_BLOCK.toString())
                )).first()));

                if(result != null) {
                    Integer x = (Integer) result.get("x");
                    Integer y = (Integer) result.get("y");
                    Integer z = (Integer) result.get("z");
                    Location location = new Location(world, x, y + 1, z);
                    player.teleport(location);
                } else{
                    sender.sendMessage("Золотой блок не установлен.");
                }
            } else if (Objects.equals(args[0], "diamond")) {
                Document result = MongoConnect.execute((collection -> collection.find(and(
                        eq("uuid", uuid.toString()),
                        eq("type", Material.DIAMOND_BLOCK.toString())
                )).first()));

                if(result != null) {
                    Integer x = (Integer) result.get("x");
                    Integer y = (Integer) result.get("y");
                    Integer z = (Integer) result.get("z");
                    Location location = new Location(world, x, y + 1, z);
                    player.teleport(location);
                }else{
                    sender.sendMessage("Алмазный блок не установлен.");
                }
            } else {
                sender.sendMessage("Wrong argument. Usage: /btp gold | diamond");
            }
        }


        return true;
    }
}
