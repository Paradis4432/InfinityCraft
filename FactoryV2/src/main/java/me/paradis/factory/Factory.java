package me.paradis.factory;

import org.bukkit.plugin.java.JavaPlugin;

public final class Factory extends JavaPlugin {

    public static Factory instance;

    @Override
    public void onEnable() {
        instance = this;

        BeltManager beltManager = new BeltManager();

        getServer().getPluginManager().registerEvents(beltManager, this);
        this.getCommand("fbelt").setExecutor(beltManager);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // save jsons of belts and others into config.yml
    }

    public static Factory getInstance() {
        return instance;
    }

    /*
    FEATURES:
    - Belts
    - Connector

    TODO:
    - particles when desired with info tool
    - working loop
    - spliters
    - generators

    V3:
    - owners, messages and alerts
    - blocks with ids of owner area
    - pause factories
    - if info tool has special addon it also adds holo on top of block
    - tool usages
    - save locations on disable
     */


}
