package me.paradis.factoryideav1;

import com.google.gson.JsonObject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.abs;

public class CommandManager implements CommandExecutor {

    String[] args;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        this.args = args;

        JsonObject belts = BeltManager.getBelts();

        if (ca0("info")){
            System.out.println(belts);
        } else if (ca0("ad")){
            int loc0 = Integer.parseInt(args[1]);
            int loc01 = Integer.parseInt(args[2]);

            System.out.println(Math.max(abs(loc0), abs(loc01)) - Math.min(abs(loc0), abs(loc01)));
            System.out.println(Math.abs(loc0 - loc01));
        }

        return false;
    }

    public boolean ca0(String arg){
        return args[0].equalsIgnoreCase(arg);
    }
}
