package me.paradis.infinitychat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onChat(AsyncPlayerChatEvent e){
        //e.setMessage(e.getPlayer().getDisplayName()  + "test: "+ e.getMessage());
        e.setFormat("prefix " + e.getPlayer().getDisplayName() + " suffix : " + e.getMessage());
    }
}
