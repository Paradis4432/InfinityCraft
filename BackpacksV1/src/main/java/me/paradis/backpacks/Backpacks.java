package me.paradis.backpacks;

import org.bukkit.plugin.java.JavaPlugin;

public final class Backpacks extends JavaPlugin {

    public static Backpacks instance;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new BackpackManager(), this);

        saveDefaultConfig();
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
