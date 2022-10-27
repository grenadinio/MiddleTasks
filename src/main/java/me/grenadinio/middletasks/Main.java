package me.grenadinio.middletasks;

import me.grenadinio.middletasks.commands.*;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class Main extends JavaPlugin {
    public static Plugin plugin;

    public final NamespacedKey GODMODE_KEY = new NamespacedKey(this, "GODMODE_KEY");
    public final NamespacedKey FLY_KEY = new NamespacedKey(this, "FLY_KEY");
    public FileConfiguration config = getConfig();
    public File myFile;
    public String helloMessage;
    public List<String> configList = config.getStringList("list");

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        plugin = this;
        helloMessage = getHelloMessage();

        this.getCommand("btp").setExecutor(new CommandBTP());
        this.getCommand("sethealth").setExecutor(new CommandSetHealth());
        this.getCommand("showtext").setExecutor(new CommandShowText());
        this.getCommand("gm").setExecutor(new CommandGm());
        this.getCommand("god").setExecutor(new CommandGod(this));
        this.getCommand("fly").setExecutor(new CommandFly(this));
        this.getCommand("addeffect").setExecutor(new CommandAddEffect());
        this.getCommand("getmin").setExecutor(new CommandGetMin());
        this.getCommand("duplicates").setExecutor(new CommandDuplicates());
        this.getCommand("addnum").setExecutor(new CommandAddNum(this));
        this.getCommand("getnum").setExecutor(new CommandGetNum(this));
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }

    @Override
    public void onDisable() {
        config.set("list", configList);
        saveConfig();
    }

    private String getHelloMessage() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        myFile = new File(getDataFolder(), "hello.yml");
        if (!myFile.exists()) {
            try {
                myFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileConfiguration myFileConfig = YamlConfiguration.loadConfiguration(myFile);
        String helloMessage;
        if (myFileConfig.get("helloString") == null) {
            helloMessage = "§6Приветствуем тебя";
            myFileConfig.set("helloString", helloMessage);
        } else {
            helloMessage = (String) myFileConfig.get("helloString");
        }
        try {
            myFileConfig.save(myFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return helloMessage;
    }
}
