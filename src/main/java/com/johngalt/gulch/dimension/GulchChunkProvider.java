package com.johngalt.gulch.dimension;

import com.johngalt.gulch.biome.GulchBiomes;
import com.johngalt.gulch.blocks.common.GaltBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

import java.util.List;
import java.util.Random;

/**
 * Created by on 6/16/2014.
 */
@SuppressWarnings("unchecked")
public class GulchChunkProvider implements IChunkProvider
{
    private World  worldObj;
    private Random random;

    public GulchChunkProvider(World world, long randomSeed)
    {
        this.worldObj = world;
        this.random = new Random(randomSeed);
        this.random.setSeed(this.worldObj.getSeed());
    }

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int chunkX, int chunkZ)
    {
        return this.provideChunk(chunkX, chunkZ);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     *
     * @param chunkX chunk location, (multiply by 16 to get world coordinate)
     * @param chunkZ chunk location, (multiply by 16 to get world coordinate)
     */
    public Chunk provideChunk(int chunkX, int chunkZ)
    {
        Chunk chunk = new Chunk(this.worldObj, chunkX, chunkZ);
        int roadHeight = 60;
        int maxBuildingHeight = 40;
        int heightInThisChunk;
        boolean isRoad;

        // Place roads
        if (chunkX % 4 == 0 || chunkZ % 3 == 0)
            isRoad = true;
        else
            isRoad = false;

        if (isRoad)
        {
            heightInThisChunk = roadHeight;
        }
        else
        {
            heightInThisChunk = roadHeight + this.random.nextInt(maxBuildingHeight);
        }

        for (int layerY = 0; layerY < heightInThisChunk; ++layerY)
        {
            Block blockForThisLayer;
            byte blockMetadataForThisLayer = 0;

            if (layerY == 0)
            {
                blockForThisLayer = Blocks.bedrock;
            }
            else if (layerY < roadHeight - 1)
            {
                blockForThisLayer = GaltBlocks.testBlock;
            }
            else if (layerY == roadHeight - 1) // off by one due to arrays starting with zero
            {
                blockForThisLayer = GaltBlocks.roadBlock;
            }
            else
            {
                blockForThisLayer = GaltBlocks.testBlock;
            }

            int chunkY = layerY >> 4; // divides by 16 and drops remainder
            ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[chunkY];

            if (extendedblockstorage == null)
            {
                extendedblockstorage = new ExtendedBlockStorage(layerY, !this.worldObj.provider.hasNoSky);
                chunk.getBlockStorageArray()[chunkY] = extendedblockstorage;
            }

            for (int xWithinChunk = 0; xWithinChunk < 16; ++xWithinChunk)
            {
                for (int zWithinChunk = 0; zWithinChunk < 16; ++zWithinChunk)
                {
                    int yWithinChunk = layerY & 15;

                    // Set block of a particular type to (X,Y,Z) local to the chunk
                    extendedblockstorage.func_150818_a(xWithinChunk, yWithinChunk, zWithinChunk, blockForThisLayer);
                    extendedblockstorage.setExtBlockMetadata(xWithinChunk, yWithinChunk, zWithinChunk,
                            blockMetadataForThisLayer);
                }
            }
        }

        byte[] gulchBiomes = chunk.getBiomeArray();
        // place roadBiome for the road
        for (int i = 0; i < gulchBiomes.length; ++i)
        {
            if (!isRoad)
                gulchBiomes[i] = (byte) GulchBiomes.roadBiome.biomeID;
            else
                gulchBiomes[i] = (byte) GulchBiomes.buildingBiome.biomeID;
        }
        chunk.setBiomeArray(gulchBiomes);

        chunk.generateSkylightMap();
        return chunk;
    }

    /**
     * Checks to see if a chunk exists at x, z
     */
    public boolean chunkExists(int x, int z)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ)
    {
        int k = chunkX * 16;
        int l = chunkZ * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
        boolean flag = false;
        long i1 = this.random.nextLong() / 2L * 2L + 1L;
        long j1 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long) chunkX * i1 + (long) chunkZ * j1 ^ this.worldObj.getSeed());
        biomegenbase.decorate(this.worldObj, this.random, k, l);
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData()
    {
        // empty
    }

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "GulchLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
        return biomegenbase.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(int par1, int par2)
    {
        // empty
    }
}
