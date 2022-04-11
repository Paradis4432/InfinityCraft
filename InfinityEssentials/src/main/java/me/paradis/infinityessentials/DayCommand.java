package me.paradis.infinityessentials;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0){
                Objects.requireNonNull(p.getLocation().getWorld()).setTime(1000);
                p.sendMessage("time set day in current world");
            } else if (args.length == 1){
                if (args[0].equalsIgnoreCase("all")){
                    for (World world : Bukkit.getServer().getWorlds()){
                        world.setTime(1000);
                    }
                    p.sendMessage("time set day in all worlds");
                }
            }
        } else {
            for (World world : Bukkit.getServer().getWorlds()){
                world.setTime(1000);
            }
            sender.sendMessage("time set day in all worlds from console");
        }


        return false;
    }
}
