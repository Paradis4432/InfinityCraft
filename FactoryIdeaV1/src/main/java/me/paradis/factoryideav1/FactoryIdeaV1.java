package me.paradis.factoryideav1;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class FactoryIdeaV1 extends JavaPlugin {

    private static FactoryIdeaV1 instance;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new ClickManager(), this);

        getServer().getPluginManager().registerEvents(new BlockPlaceEventTest(), this);

        this.getCommand("fv1").setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static FactoryIdeaV1 getInstance(){
        return instance;
    }

    /*
    for V2
    add paused and check for paused
    spliters
    generators
    working loop

     */
}
