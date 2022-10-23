package me.grenadinio.middletasks;

import me.grenadinio.middletasks.commands.*;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Plugin plugin;

    public final NamespacedKey GODMODE_KEY = new NamespacedKey(this, "GODMODE_KEY");

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        plugin = this;
        this.getCommand("btp").setExecutor(new CommandBTP());
        this.getCommand("sethealth").setExecutor(new CommandSetHealth());
        this.getCommand("showtext").setExecutor(new CommandShowText());
        this.getCommand("gm").setExecutor(new CommandGm());
        this.getCommand("god").setExecutor(new CommandGod(this));
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }

    @Override
    public void onDisable() {

    }
}
