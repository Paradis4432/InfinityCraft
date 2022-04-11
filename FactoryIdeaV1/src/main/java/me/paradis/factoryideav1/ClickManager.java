package me.paradis.factoryideav1;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickManager implements Listener {

    public static BeltManager beltManager = new BeltManager();

    @EventHandler
    public void onLeftClickBlockWithTool(PlayerInteractEvent e){
        System.out.println("event check 0");
        if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            System.out.println("event check 1");
            Player p = e.getPlayer();

            if (p.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_HOE)) {
                System.out.println("event check 2");
                if (e.getClickedBlock() != null){
                    System.out.println("event check 3");
                    beltManager.setTargetBeltConnector(p.getUniqueId(), e.getClickedBlock().getLocation());
                }
            }
        }
    }

     @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if (e.getBlock().getType().equals(Material.YELLOW_CONCRETE)){
            beltManager.addLocToBelts(e.getBlock().getLocation());
        }
     }
}
