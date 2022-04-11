package me.paradis.factoryideav1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

import static java.lang.Math.abs;

public class BeltManager {

    static JsonObject belts = new JsonObject();

    HashMap<UUID, Location> beltConnector = new HashMap<>();

    public void addLocToBelts(Location location){
        JsonObject newBelt = new JsonObject();

        newBelt.addProperty("level", 1);
        newBelt.addProperty("target", "null");

        JsonObject holding = new JsonObject();
        JsonArray filters = new JsonArray();

        newBelt.add("holding", holding);
        newBelt.add("filters", filters);

        belts.add(location.toString(), newBelt);

    }

    public void setTargetBeltConnector(UUID uuid, Location locClickedBlock){
        System.out.println("set target belt connector called: " + beltConnector);

        if (beltConnector.containsKey(uuid)){
            System.out.println("does contain");
            // target is current loc

            final Location loc0 = beltConnector.get(uuid);

            if (!locsAreAdjacent(loc0, locClickedBlock)){
                System.out.println("blocks are not adjacent");
                return;
            }

            setTarget(loc0.toString(), locClickedBlock.toString());

            beltConnector.remove(uuid);
        } else {
            System.out.println("does not contain");
            // save loc, target is next block
            beltConnector.put(uuid,locClickedBlock);
        }
    }

    public boolean locsAreAdjacent(Location loc0, Location loc1){
        if ((Math.abs(loc0.getX() - loc1.getX()) == 1) && (Math.abs(loc0.getY() - loc1.getY()) == 0) && (Math.abs(loc0.getZ() - loc1.getZ()) == 0)){
            System.out.println("diff is 1 blocks are adjacent");
            return true;
        } else if ((Math.abs(loc0.getX() - loc1.getX()) == 0) && (Math.abs(loc0.getY() - loc1.getY()) == 1) && (Math.abs(loc0.getZ() - loc1.getZ()) == 0)){
            System.out.println("diff is 1 blocks are adjacent");
            return true;
        } else if ((Math.abs(loc0.getX() - loc1.getX()) == 0) && (Math.abs(loc0.getY() - loc1.getY()) == 0) && (Math.abs(loc0.getZ() - loc1.getZ()) == 1)){
            System.out.println("diff is 1 blocks are adjacent");
            return true;
        } else {
            System.out.println("blocks are not adjacent");
            return false;
        }
    }

    public void setTarget(String loc0, String locTarget){
        System.out.println("set target called with values " + loc0);
        System.out.println("target: " + locTarget);
        belts.getAsJsonObject(loc0).addProperty("target", locTarget);
    }

    static JsonObject getBelts(){
        return belts;
    }
}
