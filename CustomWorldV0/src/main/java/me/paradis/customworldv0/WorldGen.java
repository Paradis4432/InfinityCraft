package me.paradis.customworldv0;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ALL")
public class WorldGen extends ChunkGenerator {

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world){
        return new ArrayList<BlockPopulator>();
    }

    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int cx, int cz, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        if (cx % 5 == 0 || cz % 5 == 0){
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    chunk.setBlock(x,0,z, Material.BEDROCK);
                }
            }
        }

        if (cx % 5 == 0 && cz % 5 == 0){
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    chunk.setBlock(x,0,z, Material.QUARTZ_BLOCK);
                }
            }
        }

        if (cx % 5 != 0 && cz % 5 != 0){
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    chunk.setBlock(x,0,z, Material.GRASS_BLOCK);
                }
            }
        }
        return chunk;
    }

    public byte[][] generateBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {
        System.out.println("generateBlockSections " + x + " " + z);
        return new byte[world.getMaxHeight() / 16][];
    }
}
