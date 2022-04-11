package me.paradis.customworldv0;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.event.extent.EditSessionEvent;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class CustomWorldV0 extends JavaPlugin implements Listener {

    public static CustomWorldV0 instance;
    FileConfiguration config = getConfig();


    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        this.getCommand("createtestworld").setExecutor(this);

        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public World world;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (args[0].equalsIgnoreCase("1")){
            String name = args[1];

            if (Bukkit.getWorld(name) == null){
                System.out.println("creating");

                WorldCreator wc = new WorldCreator(name)
                        .generateStructures(false)
                        .generator(new WorldGen());
                Bukkit.getServer().createWorld(wc);

                System.out.println("loading");

                world = Bukkit.getWorld(name);
                if (world != null) {
                    System.out.println("world is not null");
                    world.setDifficulty(Difficulty.NORMAL);
                }else{
                    System.out.println("world is null");
                }

            }
        } else if (args[0].equalsIgnoreCase("2")){
            String name = args[1];
            //"CustomWorldV0-1.0-SNAPSHOT"

            Player p = (Player) sender;
            Location pLoc = p.getLocation();
            World pWorld = p.getWorld();

            // File file = new File(instance.getDataFolder().getAbsolutePath() + "/test0.schem");

            // EditSession editSession = WorldEdit.getInstance().newEditSession((com.sk89q.worldedit.world.World) pWorld);


        }

        return true;
    }

}
