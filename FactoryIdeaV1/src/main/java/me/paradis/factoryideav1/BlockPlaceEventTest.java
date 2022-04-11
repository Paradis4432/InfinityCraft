package me.paradis.factoryideav1;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public class BlockPlaceEventTest implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Location location = e.getBlock().getLocation();

        //e.getPlayer().spawnParticle(Particle.TOTEM, e.getPlayer().getLocation(), 1, 0, 0, 0);

        //e.getPlayer().spawnParticle(Particle.TOTEM, location, 1, 0, 0, 0);



        for (int i = 0; i < 5; i++){
            Bukkit.getScheduler().scheduleSyncDelayedTask(FactoryIdeaV1.getInstance(), new Runnable() {
                @Override
                public void run() {
                    //System.out.println("spawning");
                    spawn(e.getPlayer(), location);
                }
            }, i*5L);
        }

        if (e.getBlock().getType().equals(Material.BEDROCK)){
            // find the closest bedrock, create a line, join them
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
        double minX = location.getBlockX();
        double minY = location.getBlockY();
        double minZ = location.getBlockZ();
        double maxX = location.getBlockX() + 1;
        double maxY = location.getBlockY() + 1;
        double maxZ = location.getBlockZ() + 1;

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
