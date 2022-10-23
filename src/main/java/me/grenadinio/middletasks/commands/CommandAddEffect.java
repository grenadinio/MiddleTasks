package me.grenadinio.middletasks.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandAddEffect implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }

        if (args.length == 3) {
            PotionEffectType potionEffectType = PotionEffectType.getByName(args[0]);
            int effectAmplifier = Integer.parseInt(args[1]);
            int effectDuration = Integer.parseInt(args[2]);

            if (potionEffectType != null) {
                Bukkit.getServer().getOnlinePlayers().forEach((Player) ->
                        Player.addPotionEffect(new PotionEffect(potionEffectType, effectDuration, effectAmplifier)));
            } else {
                sender.sendMessage("Неверно указан эффект. Пример: WEAKNESS");
            }

        } else {
            sender.sendMessage("Неверно введена команда. Использование: /addeffect <effect> <effectAmplifier> <effectDuration>");
        }

        return true;
    }

}
