package me.paradis.backpacks;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.sun.tools.javac.jvm.Items;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RightClickManager implements Listener {


    HashMap<UUID, Inventory> backpacks = new HashMap<>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().getType().equals(Material.ENDER_CHEST)){
                System.out.println("here called");
                if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("backpack ID:")) {
                    //open backpack

                    FileConfiguration c = Backpacks.getInstance().getConfig();
                    String name = e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                    int id = Integer.parseInt(name.split(":")[1]);
                    String ids = String.valueOf(id);

                    System.out.println("getting items from config and showing them");
                    // create and save inv to listen when it closes

                    ChestGui gui = new ChestGui(6, name);

                    gui.setOnClose(event -> {
                        // save id and items
                        ItemStack[] items = event.getInventory().getContents();
                        System.out.println("saving, items:");
                        System.out.println(Arrays.toString(items));

                        c.set(ids, null);
                        for (int i = 0; i < items.length; i++) {
                            if (items[i] == null) continue;
                            c.set(ids + "." + i, items[i]);
                            System.out.println("set new item as " + ids + "." + i + " with item: " + items[i]);
                        }
                        Backpacks.getInstance().saveConfig();

                    });

                    if (c.getString(ids) == null){
                        gui.show(p);
                    } else {
                        //OutlinePane pane = new OutlinePane(0,0,9, 6);

                        // add items to pane and show them

                        if (c.getConfigurationSection(ids) != null){
                            Set<String> keys = c.getConfigurationSection(ids).getKeys(false);

                            ItemStack[] items = new ItemStack[keys.size()];
                            int i = 0;

                            for (String key : keys){
                                items[i++] = (ItemStack) c.get(ids + "." + key);
                            }

                            System.out.println(Arrays.toString(items));

                            for (ItemStack item : items){
                                //pane.addItem(new GuiItem(item));
                                gui.getInventory().addItem(item);
                            }

                            //Inventory inv = gui.getInventory();
                            //for (ItemStack item : inv.getContents()){
                            //    if (item == null || item.equals(Material.AIR)) continue;
                            //    inv.remove(item);
                            //    System.out.println("removing the shitty key");
                            //    NBTItem nbtItem = new NBTItem(item);
                            //    System.out.println(nbtItem.getKeys());
                            //    nbtItem.removeKey("PublicBukkitValues");
                            //    item = nbtItem.getItem();
                            //    System.out.println(nbtItem.getKeys());
                            //    inv.addItem(item);
                            //}
//
                            //for (ItemStack item : inv.getContents()){
                            //    if (item == null || item.equals(Material.AIR)) continue;
                            //    gui.getInventory().addItem(item);
                            //}

                            gui.show(p);
                        }
                        else System.out.println("ids not found");
                    }

                    // add the items
                    // show inv


                }
            }
        }
    }

    @EventHandler
    public void cancelEnderChestBlockPlace(BlockPlaceEvent e){
        if (e.getBlock().getType().equals(Material.ENDER_CHEST)){
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("backpack")){
                e.setCancelled(true);
            }
        }
    }

}

/*
old code

String name = e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                    ChestGui gui = new ChestGui(6, name);

                    int id = Integer.parseInt(name.split(":")[1]);
                    String ids = String.valueOf(id);

                    // String itemStacks = Backpacks.getInstance().getConfig().getString(String.valueOf(id));

                    if (!((Backpacks.getInstance().getConfig().get(ids)) == null)){

                        OutlinePane items = new OutlinePane (0, 0, 9, 6);

                        //ArrayList<ItemStack> content = (ArrayList<ItemStack>) Backpacks.getInstance().getConfig().getList(id + ".content");
                        //noinspection unchecked
                        ItemStack[] content = ((List<ItemStack>) Backpacks.getInstance().getConfig().get(ids)).toArray(new ItemStack[0]);

                        System.out.println("content: ");
                        System.out.println(content);

                        //ItemStack[] itemStacks = new ItemStack[content.size()];

                        //System.out.println(Arrays.toString(itemStacks));

                        // for (int i = 0; i < content.size(); i++){
                        //     ItemStack item = content.get(i);
                        //     if (item != null) itemStacks[i] = item;
                        //     else itemStacks[i] = null;
                        // }
                        // System.out.println(Arrays.toString(itemStacks));

                        for (ItemStack i : content){
                            //items.addItem(new GuiItem(i));
                            System.out.println(i);
                        }

                        gui.addPane(items);
                    }

                    gui.setOnClose(event -> {
                        Backpacks.getInstance().getConfig().set(ids, event.getInventory().getContents());

                        //Backpacks.getInstance().getConfig().set(String.valueOf(id) + ".content", event.getInventory().getContents());
                        Backpacks.getInstance().saveConfig();
                    });


                    gui.show(p);
 */
