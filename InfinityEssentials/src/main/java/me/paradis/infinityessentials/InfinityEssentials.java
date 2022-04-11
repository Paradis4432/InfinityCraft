package me.paradis.infinityessentials;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class InfinityEssentials  extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("day").setExecutor(new DayCommand());
        getLogger().info("Infinity Essentials Loaded");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent event) {

        System.out.println("rain cancelled");
        boolean rain = event.toWeatherState();
        if(rain)
            event.setCancelled(true);
    }

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent event) {

        System.out.println("rain cancelled");
        boolean storm = event.toThunderState();
        if(storm)
            event.setCancelled(true);
    }
}
