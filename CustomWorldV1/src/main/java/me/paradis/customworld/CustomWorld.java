package me.paradis.customworld;

import org.bukkit.plugin.java.JavaPlugin;

public final class CustomWorld extends JavaPlugin {

    public static CustomWorld instance;
    
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new BlockPlaceEventManager(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public static CustomWorld getInstance(){
        return instance;
    }
}
