package me.paradis.backpacks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class BackpackManager implements Listener {

    FileConfiguration c = Backpacks.getInstance().getConfig();
    // inventory | backpack id
    HashMap<Inventory, Integer> backpacks = new HashMap<>();

    @EventHandler
    public void onRightClickEnderChest(PlayerInteractEvent e){
        if (!e.getAction().equals(Action.RIGHT_CLICK_AIR)) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.ENDER_CHEST)) return;

        Player p = e.getPlayer();
        String itemName = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if (!itemName.contains("backpack ID:")) return;

        String ids = itemName.split(":")[1];
        int id = Integer.parseInt(ids);

        if (c.getString(ids) == null) {
            // player never used this backpack
            openEmptyBackpack(itemName, p, id);

        }else{
            openBackpack(id, itemName, p);
        }

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        // get items in inv and save them
        if (backpacks.containsKey(e.getInventory())){
            Inventory inv = e.getInventory();
            String ids = String.valueOf(backpacks.get(inv));

            ItemStack[] items = e.getInventory().getContents();

            c.set(ids, null);
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null) continue;
                c.set(ids + "." + i, items[i]);
            }

            backpacks.remove(e.getInventory());
        }
    }

    @EventHandler
    public void cancelEnderChestBlockPlace(BlockPlaceEvent e){
        if (e.getBlock().getType().equals(Material.ENDER_CHEST)){
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("backpack ID:")){
                e.setCancelled(true);
            }
        }
    }

    public void openEmptyBackpack(String name, Player p, int id){
        Inventory inv = Bukkit.createInventory(null, 54, name);
        p.openInventory(inv);
        backpacks.put(inv, id);
    }

    public void openBackpack(int id, String name, Player p){
        // get items from config and load them into a inventory
        Inventory inv = Bukkit.createInventory(null, 54, name);
        backpacks.put(inv, id);

        String ids = String.valueOf(id);

        Set<String> keys = c.getConfigurationSection(ids).getKeys(false);

        ItemStack[] items = new ItemStack[keys.size()];
        int i = 0;

        for (String key : keys){
            items[i++] = (ItemStack) c.get(ids + "." + key);
        }

        System.out.println(Arrays.toString(items));

        for (ItemStack item : items){
            //pane.addItem(new GuiItem(item));
            inv.addItem(item);
        }

        p.openInventory(inv);

    }
}
