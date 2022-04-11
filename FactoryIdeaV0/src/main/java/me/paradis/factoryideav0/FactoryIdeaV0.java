package me.paradis.factoryideav0;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public final class FactoryIdeaV0 extends JavaPlugin implements Listener {

    JsonObject belts = new JsonObject();
    Integer cont = 0;

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(this,this);

        this.getCommand("jsonTest").setExecutor(this);

        this.getCommand("test").setExecutor(new ClaseTest());

    }

    public void startLoop(){

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            System.out.println("this works");


        }, 0, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        System.out.println("this works");

        if (e.getBlock().getType().equals(Material.YELLOW_CONCRETE)) addLocToBelts(e.getBlock().getLocation());
    }


    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        System.out.println("this also works");
        final Player p = e.getPlayer();


        // if (e.getHand() == EquipmentSlot.HAND) return;

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK){

            if (e.getClickedBlock().getType().equals(Material.YELLOW_CONCRETE)){
            }
        } else if (e.getAction() == Action.LEFT_CLICK_BLOCK){

            if (e.getClickedBlock().getType().equals(Material.YELLOW_CONCRETE)){
                if (p.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_HOE)){

                }
            }
        }
    }

    public void addLocToBelts(Location location){

        JsonObject loc = new JsonObject();

        loc.addProperty("level", 1);
        loc.addProperty("target", "null");

        JsonObject holding = new JsonObject();

        holding.addProperty("item" + cont, 1);
        cont++;
        holding.addProperty("item" + cont, 10);
        cont++;
        holding.addProperty("item" + cont, 11);
        cont++;
        holding.addProperty("item" + cont, 8);
        cont++;

        loc.add("holding", holding);

        JsonArray filters = new JsonArray();

        loc.add("filters", filters);

        belts.add(location.toString(), loc);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        p.sendMessage("nicuuu");

        if (args[0].equalsIgnoreCase("2")){
            JsonObject market = new JsonObject();
            JsonObject loc = new JsonObject();


            loc.addProperty("speedLevel", "X");
            loc.addProperty("amountLevel", "X");
            loc.addProperty("holdLevel", "X");

            JsonObject stored = new JsonObject();

            stored.addProperty("item0", "X");
            stored.addProperty("item1", "X");

            loc.add("stored", stored);

            market.add("loc", loc);

            market.add("loc1", loc);

            System.out.println(market);

            try{
                System.out.println((market.getAsJsonObject("loc")).get("stored"));

            }catch (Exception e){
                System.out.println("something got fucked up 2");
            }
        }
        else if (args[0].equalsIgnoreCase("3")){

            JsonObject loc = new JsonObject();

            loc.addProperty("level", 1);
            loc.addProperty("target", "K");

            JsonObject holding = new JsonObject();

            ItemStack item0 = new ItemStack(Material.STONE);
            ItemStack item1 = new ItemStack(Material.DIAMOND_BLOCK);

            holding.addProperty(item0.toString(), 3);
            holding.addProperty(item1.toString(), 5);

            loc.add("holding", holding);

            JsonArray filters = new JsonArray();
            filters.add("item0");
            filters.add("item1");
            filters.add("item2");

            loc.add("filters", filters);

            belts.add("loc", loc);

            System.out.println(belts);
        } else if (args[0].equalsIgnoreCase("4")){
            System.out.println(belts.getAsJsonObject("loc").get("holding"));
        } else if (args[0].equalsIgnoreCase("info")){
            System.out.println(belts);
        } else if (args[0].equalsIgnoreCase("set")){
            JsonObject data = belts.getAsJsonObject("Location{world=CraftWorld{name=working0},x=-37.0,y=1.0,z=59.0,pitch=0.0,yaw=0.0}");
            System.out.println(data);
            data.remove("holding");
            System.out.println("after removing");
            System.out.println(data);
            data.addProperty("holding2", "test");
            System.out.println(data);
        } else if (args[0].equalsIgnoreCase("set2")){
            JsonObject data = belts.getAsJsonObject("Location{world=CraftWorld{name=working0},x=-37.0,y=1.0,z=59.0,pitch=0.0,yaw=0.0}");
            System.out.println(data);
            data.addProperty("holding", "test");
            System.out.println(data);

        } else if (args[0].equalsIgnoreCase("set3")){
            JsonObject data = belts.getAsJsonObject("Location{world=CraftWorld{name=working0},x=-37.0,y=1.0,z=59.0,pitch=0.0,yaw=0.0}");

            JsonObject dataHolding = data.getAsJsonObject("holding");

            System.out.println(dataHolding);

            dataHolding.addProperty("testDataAdd", "valueTest");

            System.out.println(dataHolding);

            System.out.println(data);

            data.add("holding", dataHolding);

            System.out.println(data);
        } else if (args[0].equalsIgnoreCase("replace")){
            JsonObject data = belts.getAsJsonObject("Location{world=CraftWorld{name=working0},x=-37.0,y=1.0,z=59.0,pitch=0.0,yaw=0.0}");

            JsonObject dataHolding = data.getAsJsonObject("holding");

            dataHolding.addProperty("item2", "this is a replace test");

            data.add("holding", dataHolding);

            System.out.println(data);
        } else if (args[0].equalsIgnoreCase("replaceValue")){
            System.out.println("before change:");
            System.out.println(belts);
            String location = "Location{world=CraftWorld{name=working0},x=-37.0,y=1.0,z=59.0,pitch=0.0,yaw=0.0}";
            String key = args[1];
            String newValue = args[2];
            replaceValue(location, key, newValue);
            System.out.println(belts);
        } else if (args[0].equalsIgnoreCase("deleteValue")){
            System.out.println("before change:");
            System.out.println(belts);
            String location = "Location{world=CraftWorld{name=working0},x=-37.0,y=1.0,z=59.0,pitch=0.0,yaw=0.0}";
            String key = args[1];
            deleteValue(location, key);
            System.out.println(belts);
        } else if (args[0].equalsIgnoreCase("list")){
            System.out.println(belts.entrySet());
            for (String i : belts.keySet()){
                System.out.println(i);
            }
            System.out.println(belts.keySet());
        }

        return true;
    }

    public void replaceValue(String location, String key, String newValue){
        belts.getAsJsonObject(location).getAsJsonObject("holding").addProperty(key, newValue);
    }

    public void deleteValue(String location, String key){
        belts.getAsJsonObject(location).getAsJsonObject("holding").remove(key);
    }

    public void setTarget(String location, String newLoc){
        belts.getAsJsonObject(location).addProperty("target", newLoc);
    }

}

