package me.paradis.factory;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ParticleManager {

    public void spawnGreenParticlesAroundBlock(Player p, Location location){
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
