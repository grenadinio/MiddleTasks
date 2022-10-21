package me.grenadinio.middletasks;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("btp").setExecutor(new CommandBTP());
        this.getCommand("sethealth").setExecutor(new CommandSetHealth());
        this.getCommand("showtext").setExecutor(new CommandShowText());
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
