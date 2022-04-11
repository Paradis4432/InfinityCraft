package me.paradis.factory;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Utils {

    /**
     * example:
     * {"Location{world=CraftWorld{name=working0},x=-102.0,y=1.0,z=99.0,pitch=0.0,yaw=0.0}":
     *  {"level":1,"target":"Location{world=CraftWorld{name=working0},x=-103.0,y=1.0,z=99.0,pitch=0.0,yaw=0.0}","holding":{},"filters":[]}
     *
     * @param loc the location as string
     * @return a new Location from the string
     */
    public Location ScrapLocationFromString(String loc){
        String[] parts = loc.split(",");
        World world = Bukkit.getWorld(parts[0].split("name=")[1].replace("\\}", ""));
        parts[1] = parts[1].replace("=", "");
        parts[2] = parts[2].replace("=", "");
        parts[3] = parts[3].replace("=", "");

        parts[1] = parts[1].replace("x", "");
        parts[2] = parts[2].replace("y", "");
        parts[3] = parts[3].replace("z", "");

        return new Location(world, Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
    }
}
