package me.paradis.infinitychat;

import org.bukkit.plugin.java.JavaPlugin;

public final class InfinityChat extends JavaPlugin {

    public static InfinityChat instance;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new ChatManager(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static InfinityChat getInstance() {
        return instance;
    }
}
