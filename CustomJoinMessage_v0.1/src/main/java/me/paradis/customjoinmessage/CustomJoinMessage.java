package me.paradis.customjoinmessage;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class CustomJoinMessage extends JavaPlugin implements Listener {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        //noinspection ConstantConditions
        this.getCommand("cmessage").setExecutor(this);

        saveDefaultConfig();
        getLogger().info("Custom Join Message Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String playerId = event.getPlayer().getUniqueId().toString();

        if (config.getString(playerId) == null){
            player.sendMessage("Welcome " + player.getDisplayName());
        }else{
            player.sendMessage(Objects.requireNonNull(config.getString(playerId + ".message")));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){

            sender.sendMessage("cant use this in console!");
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0 || args.length == 1 || args.length == 2){
            return false;
        }

        if (args[0].equalsIgnoreCase("set")){
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null){
                p.sendMessage("player not found");
                return false;
            }

            if (!(target.isOnline())) p.sendMessage("target cannot be offline");

            StringBuilder message = new StringBuilder();
            for (int i = 2; i < args.length; i++){
                message.append(args[i]).append(" ");
            }

            String targetUniqueID = String.valueOf(target.getUniqueId());
            config.set(targetUniqueID + ".message" , message.toString());
            config.set(targetUniqueID + ".name" , target.getName());

            // ItemStack item0 = new ItemStack(Material.STONE, 10);
            // ItemStack item1 = new ItemStack(Material.GOLD_BLOCK, 5);
            // ItemStack item2 = new ItemStack(Material.DIAMOND_BLOCK, 3);
//
            // ArrayList<ItemStack> items = new ArrayList<>();
//
            // items.add(item0);
            // items.add(item1);
            // items.add(item2);
//
            // System.out.println("item0");
            // System.out.println(item0);
            // System.out.println("item0 serialized");
            // System.out.println(item0.serialize());
//
            // System.out.println("items: ");
            // System.out.println(items);
//
            // config.set(targetUniqueID + ".items", items);
            // saveConfig();
//
            // List<ItemStack> newItemList = new ArrayList<>();
//
            // System.out.println("maplist in config:");
            // System.out.println(config.getMapList(targetUniqueID + ".items"));
//
            // System.out.println("data in UUID:");
            // System.out.println(config.get(targetUniqueID));
//
            // for (Map<?, ?> itemList : config.getMapList(targetUniqueID + ".items")){
            //     System.out.println("found item in items");
            //     newItemList.add(ItemStack.deserialize((Map<String, Object>) itemList));
            // }
//
            // for (ItemStack item : newItemList){
            //     System.out.println("printing each item");
            //     System.out.println(item);
            // }

            p.sendMessage("message of " + target.getName() + " changed to: " + message);


        } else if(args[0].equalsIgnoreCase("delete")){
            Player target = Bukkit.getPlayer(args[1]);

            if (target != null){
                config.set(String.valueOf(target.getUniqueId()), null);
                p.sendMessage("message of " + target.getName() + " deleted!");

            }
        }

        return true;
    }
}
