package me.grenadinio.middletasks;

import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoined(PlayerJoinEvent event){
        ItemStack diamondBlock = new ItemStack(Material.DIAMOND_BLOCK,1);
        ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK,1);

        event.getPlayer().getInventory().addItem(diamondBlock,goldBlock);
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        Location location = event.getBlock().getLocation();
        Material material = event.getBlock().getType();

        if(material == Material.DIAMOND_BLOCK || material == Material.GOLD_BLOCK){
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
    public void onChat(AsyncPlayerChatEvent event){
        String message = event.getMessage();
        String coloredString = ChatColor.translateAlternateColorCodes('&', message);
        event.setMessage(coloredString);
    }
}
