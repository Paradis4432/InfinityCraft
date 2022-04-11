package me.paradis.factoryideav0;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClaseTest implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            p.sendMessage("gap puto xd");
        }

        return false;
    }
}
