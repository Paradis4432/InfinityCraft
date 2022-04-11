package me.paradis.customworld;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class BlockPlaceEventManager implements Listener {

    BukkitTask box;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if (e.getBlock().getType().equals(Material.ORANGE_CONCRETE)) {
            Location location = e.getBlock().getLocation();
        }
    }

    public void spawn(Player p, Location location){
        for (Location l : getParticleLocations(location)){
            p.spawnParticle(Particle.REDSTONE, l, 1, 0,0,0, new Particle.DustOptions(Color.GREEN, 0.4f));
        }
    }

    public List<Location> getParticleLocations(Location location) {
        List<Location> result = new ArrayList<>();
        World world = location.getWorld();
        double minX = location.getBlockX() - 5;
        double minY = location.getBlockY() - 5;
        double minZ = location.getBlockZ() - 5;
        double maxX = location.getBlockX() + 6;
        double maxY = location.getBlockY() + 6;
        double maxZ = location.getBlockZ() + 6;

        for (double x = minX; x <= maxX; x = Math.round((x + 0.05) * 1e2) / 1e2) {
            for (double y = minY; y <= maxY; y = Math.round((y + 0.05) * 1e2) / 1e2) {
                for (double z = minZ; z <= maxZ; z = Math.round((z + 0.05) * 1e2) / 1e2) {
                    int components = 0;
                    if (x == minX || x == maxX) components++;
                    if (y == minY || y == maxY) components++;
                    if (z == minZ || z == maxZ) components++;

                    if (components >= 2) {
                        result.add(new Location(world, x, y, z));
                    }
                }
            }
        }
        return result;
    }

}
