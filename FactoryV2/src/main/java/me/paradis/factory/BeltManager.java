package me.paradis.factory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


/**
 * main belt class, manages the block placement, tool usage, constant loops, and belt related commands
 * main command: fbelt [args]
 * listening events: blockPlaceEvent, PlayerInteractEvent
 *
 */
public class BeltManager implements CommandExecutor, Listener{

    // json object holding all belts
    static JsonObject belts = new JsonObject();

    // cache type of hashmap to connect two locations
    HashMap<UUID, Location> beltConnector = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("info")){
            System.out.println(belts);
        } else if (args[0].equalsIgnoreCase("info1")){
            System.out.println(beltConnector);
        }

        return false;
    }

    /**
     * checks if the placed block is yellow concrete, and if the name is not belt it cancels the event
     * in case name is belt, saves location
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if (!e.getBlock().getType().equals(Material.YELLOW_CONCRETE)) return;

        String itemName = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();

        if (!itemName.equalsIgnoreCase("belt")){
            p.sendMessage("cant place this block");
            e.setCancelled(true);
        }

        createAndSaveBeltLocation(e.getBlock().getLocation());
    }

    /**
     * if clicked block is not yellow concrete : return
     *
     * while using connector tool:
     *  on left click block: sets origin
     *  on right click block: sets target if adjacent
     *
     * while using info tool:
     *  on left click block: gets a full info display of block todo
     */
    @EventHandler
    public void onYellowConcreteClick(PlayerInteractEvent e){
        if (!Objects.requireNonNull(e.getHand()).equals(EquipmentSlot.HAND)) return;
        if (!Objects.requireNonNull(e.getClickedBlock()).getType().equals(Material.YELLOW_CONCRETE))return;

        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        Player p = e.getPlayer();

        if (item.getType().equals(Material.GOLDEN_HOE) &&
                item.getItemMeta().getDisplayName().equalsIgnoreCase("connector")){

            // player is using connector

            if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                // sets origin
                // send player message
                p.sendMessage("left clicked block with connector");
                setOrigin(p.getUniqueId(), e.getClickedBlock().getLocation());

            } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                // sets target
                // send player message
                p.sendMessage("right clicked block with connector");
                setTarget(p, e.getClickedBlock().getLocation());
            }
        } else if (item.getType().equals(Material.WOODEN_HOE) &&
                item.getItemMeta().getDisplayName().equalsIgnoreCase("info")){

            // player is using info tool

            if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                // send player info of belt
                // show paticle on target
                p.sendMessage("left clicked block with info");

                // gets Location loc of clicked block, turns it to string
                // converts the target of loc from string to Location
                // spawns particles there
                String loc = e.getClickedBlock().getLocation().toString();
                new ParticleManager().spawnGreenParticlesAroundBlock(p, getTargetOfLocation(loc));
            }
        }
    }

    public void createAndSaveBeltLocation(Location location){
        JsonObject newBelt = new JsonObject();

        newBelt.addProperty("level", 1);
        newBelt.addProperty("target", "null");

        JsonObject holding = new JsonObject();
        JsonArray filters = new JsonArray();

        newBelt.add("holding", holding);
        newBelt.add("filters", filters);

        belts.add(location.toString(), newBelt);
    }

    /**
     * sets the origin of clicked block
     * @param uuid of player to look for in beltConnector
     * @param location of clicked block
     */
    public void setOrigin(UUID uuid, Location location){
        beltConnector.put(uuid,location);
    }

    /**
     * sets the target of clicked block
     * @param player performing the action
     * @param location of clicked block
     */
    public void setTarget(Player player, Location location){
        UUID uuid = player.getUniqueId();
        Location loc0 = beltConnector.get(uuid);

        if (!locsAreAdjacent(loc0, location)){
            player.sendMessage("blocks are not adjacent");
            return;
        }

        belts.getAsJsonObject(loc0.toString()).addProperty("target", location.toString());

        beltConnector.remove(uuid);
    }

    /**
     *
     * @param locAsString of clicked block
     * @return the location of target used mainly to show info of block
     */
    public Location getTargetOfLocation(String locAsString){
        return new Utils().ScrapLocationFromString(belts.getAsJsonObject(locAsString).get("target").toString());
    }

    /*
    replace with a count, check each condition, if condition == true, add one to count, return true when count = 1,
    otherwise return false
     */
    public boolean locsAreAdjacent(Location loc0, Location loc1){

        if ((Math.abs(loc0.getX() - loc1.getX()) == 1) && (Math.abs(loc0.getY() - loc1.getY()) == 0) && (Math.abs(loc0.getZ() - loc1.getZ()) == 0)){
            return true;
        } else if ((Math.abs(loc0.getX() - loc1.getX()) == 0) && (Math.abs(loc0.getY() - loc1.getY()) == 1) && (Math.abs(loc0.getZ() - loc1.getZ()) == 0)){
            return true;
        } else if ((Math.abs(loc0.getX() - loc1.getX()) == 0) && (Math.abs(loc0.getY() - loc1.getY()) == 0) && (Math.abs(loc0.getZ() - loc1.getZ()) == 1)){
            return true;
        } else {
            System.out.println("blocks are not adjacent");
            return false;
        }
    }

}
