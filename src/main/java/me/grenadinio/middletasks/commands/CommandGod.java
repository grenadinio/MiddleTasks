package me.grenadinio.middletasks.commands;

import me.grenadinio.middletasks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class CommandGod implements CommandExecutor {
    private final Main plugin;

    public CommandGod(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }
        Player player = (Player) sender;
        boolean enabled = player.getPersistentDataContainer().has(plugin.GODMODE_KEY, PersistentDataType.INTEGER);

        if (args.length != 0) {
            sender.sendMessage("Команда не поддерживает аргументы.");
        } else {
            if (!enabled) {
                player.getPersistentDataContainer().set(plugin.GODMODE_KEY, PersistentDataType.INTEGER, 1);
                player.sendMessage(String.format("Игроку %s включен режим бога.", player.getName()));
            } else {
                player.getPersistentDataContainer().remove(plugin.GODMODE_KEY);
                player.sendMessage(String.format("Игроку %s выключен режим бога.", player.getName()));
            }
        }

        return true;
    }
}
