package me.paradis.factorytoolsv0;

import de.tr7zw.nbtapi.NBTItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class FactoryToolsV0 extends JavaPlugin implements Listener {

    NamespacedKey key;

    @Override
    public void onEnable() {
        // Plugin startup logic
        FactoryToolsV0 instance = this;

        getServer().getPluginManager().registerEvents(this,this);

        this.getCommand("tagtest").setExecutor(this);

        this.key = new NamespacedKey(instance, "custom-key");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        final Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_HOE)){
            if (e.getAction() == Action.RIGHT_CLICK_AIR) {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("this is a test"));





            }
        }
    }
    ItemStack item = new ItemStack(Material.STONE);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("1")){
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, Math.PI);
            item.setItemMeta(meta);
        }

        else if (args[0].equalsIgnoreCase("2")){
            System.out.println(item);
        }
        else if (args[0].equalsIgnoreCase("3")){
            PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();

            if (container.has(key, PersistentDataType.DOUBLE)){
                double found = container.get(key, PersistentDataType.DOUBLE);
                System.out.println(found);
            }
        }
        else if (args[0].equalsIgnoreCase("4")){
            System.out.println(key);
        }

        return true;
    }
}
