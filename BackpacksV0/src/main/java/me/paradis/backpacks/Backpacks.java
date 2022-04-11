package me.paradis.backpacks;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Backpacks extends JavaPlugin {

    FileConfiguration config = getConfig();
    public static Backpacks instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new RightClickManager(), this);

        this.getCommand("newbackpack").setExecutor(new CommandManager());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }

    public static Backpacks getInstance(){
        return instance;
    }
}
