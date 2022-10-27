package me.grenadinio.middletasks;

import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class EventListener implements Listener {
    private final Main plugin;

    public EventListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoined(PlayerJoinEvent event) {
        ItemStack diamondBlock = new ItemStack(Material.DIAMOND_BLOCK, 1);
        ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK, 1);

        event.getPlayer().getInventory().addItem(diamondBlock, goldBlock);

        event.setJoinMessage(plugin.helloMessage);
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        Location location = event.getBlock().getLocation();
        Material material = event.getBlock().getType();

        if (material == Material.DIAMOND_BLOCK || material == Material.GOLD_BLOCK) {
            MongoConnect.execute((collection) -> {
                collection.insertOne(new Document()
                        .append("uuid", uuid.toString())
                        .append("type", material.toString())
                        .append("x", location.getBlockX())
                        .append("y", location.getBlockY())
                        .append("z", location.getBlockZ()));
                return null;
            });
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        String coloredString = ChatColor.translateAlternateColorCodes('&', message);
        event.setMessage(coloredString);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player
                && event.getEntity().getPersistentDataContainer().has(plugin.GODMODE_KEY, PersistentDataType.INTEGER)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.LAVA
                || event.getCause() == EntityDamageEvent.DamageCause.FIRE
                || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
            if (event.getEntity() instanceof Player
                    && event.getEntity().getPersistentDataContainer().has(plugin.GODMODE_KEY, PersistentDataType.INTEGER)) {
                event.setCancelled(true);
            }
        }
    }
}
