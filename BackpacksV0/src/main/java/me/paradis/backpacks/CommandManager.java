package me.paradis.backpacks;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args[0].equalsIgnoreCase("get")){
            ItemStack backpack = new ItemStack(Material.ENDER_CHEST);
            ItemMeta backpackMeta = backpack.getItemMeta();

            String uniqueID = getUniqueBackpackID();
            backpackMeta.setDisplayName("backpack ID:" + uniqueID);

            backpack.setItemMeta(backpackMeta);

            Player p = (Player) sender;

            p.getInventory().addItem(backpack);
        } else if (args[0].equalsIgnoreCase("save")){
            Backpacks.getInstance().saveConfig();
        } else if (args[0].equalsIgnoreCase("set")){
            String path = args[1];
            int id = Integer.parseInt(args[2]);

            Backpacks.getInstance().getConfig().set(path, id);
            Backpacks.getInstance().saveConfig();
        } else if (args[0].equalsIgnoreCase("testUUID")){
            Player p = (Player) sender;
            ItemStack item = p.getInventory().getItemInMainHand();

            NBTItem nbtItem = new NBTItem(item);

            System.out.println("keys");
            System.out.println(nbtItem.getKeys());
            System.out.println("attempting to remove");
            nbtItem.removeKey("PublicBukkitValues");
            System.out.println("new Keys:");
            System.out.println(nbtItem.getKeys());
            System.out.println("attempting to update");
            p.getInventory().remove(item);
            item = nbtItem.getItem();
            p.getInventory().setItemInMainHand(item);
            System.out.println("new keys after refresh:");
            System.out.println(nbtItem.getKeys());
        }


        return false;
    }

    public String getUniqueBackpackID(){
        int id = Backpacks.getInstance().getConfig().getInt("nextID");
        id = id + 1;
        Backpacks.getInstance().getConfig().set("nextID", id);
        return String.valueOf(id);
    }
}
