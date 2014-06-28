package com.johngalt.gulch.dimension;

import com.johngalt.gulch.blocks.GaltBlocks;
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
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.FlatLayerInfo;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by on 6/16/2014.
 */
@SuppressWarnings("unchecked")
public class GulchChunkProvider implements IChunkProvider
{
    private World worldObj;
    private Random random;
    private int layersFilled;
    private final Block[] cachedBlockIDs = new Block[256];
    private final byte[] cachedBlockMetadata = new byte[256];
    private final FlatGeneratorInfo flatWorldGenInfo;
    private final List<MapGenStructure> structureGenerators = new ArrayList<MapGenStructure>();
    private final boolean hasDecoration;
    private final boolean hasDungeons;
    private WorldGenLakes waterLakeGenerator;
    private WorldGenLakes lavaLakeGenerator;
    private static final String __OBFID = "CL_00000391";

    public GulchChunkProvider(World world, long randomSeed)
    {
        this.worldObj = world;
        this.random = new Random(randomSeed);
        this.random.setSeed(this.worldObj.getSeed());
        this.flatWorldGenInfo = getGulchWorldGenerator();

        this.hasDecoration = this.flatWorldGenInfo.getWorldFeatures().containsKey("decoration");

        if (this.flatWorldGenInfo.getWorldFeatures().containsKey("lake"))
        {
            this.waterLakeGenerator = new WorldGenLakes(Blocks.water);
        }

        if (this.flatWorldGenInfo.getWorldFeatures().containsKey("lava_lake"))
        {
            this.lavaLakeGenerator = new WorldGenLakes(Blocks.lava);
        }

        this.hasDungeons = this.flatWorldGenInfo.getWorldFeatures().containsKey("dungeon");
        Iterator iterator = this.flatWorldGenInfo.getFlatLayers().iterator();

        while (iterator.hasNext())
        {
            FlatLayerInfo flatlayerinfo = (FlatLayerInfo) iterator.next();

            for (int j = flatlayerinfo.getMinY(); j < flatlayerinfo.getMinY() + flatlayerinfo.getLayerCount(); ++j)
            {
                this.layersFilled++;
                this.cachedBlockIDs[j] = flatlayerinfo.func_151536_b();
                this.cachedBlockMetadata[j] = (byte) flatlayerinfo.getFillBlockMeta();
            }
        }
    }

    public static FlatGeneratorInfo getGulchWorldGenerator()
    {
        FlatGeneratorInfo flatgeneratorinfo = new FlatGeneratorInfo();
        flatgeneratorinfo.setBiome(BiomeGenBase.plains.biomeID);
        flatgeneratorinfo.getFlatLayers().add(new FlatLayerInfo(1, Blocks.bedrock));
        flatgeneratorinfo.getFlatLayers().add(new FlatLayerInfo(60, GaltBlocks.testBlock));
        flatgeneratorinfo.func_82645_d();
        return flatgeneratorinfo;
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

        // Place roads
        if (chunkX % 4 == 0 || chunkZ % 4 == 0)
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
                    extendedblockstorage.setExtBlockMetadata(xWithinChunk, yWithinChunk, zWithinChunk, blockMetadataForThisLayer);
                }
            }
        }

        chunk.generateSkylightMap();
        BiomeGenBase[] biomeGenBases = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[]) null, chunkX * 16, chunkZ * 16, 16, 16);
        byte[] biomeArray = chunk.getBiomeArray();

        for (int i = 0; i < biomeArray.length; ++i)
        {
            biomeArray[i] = (byte) biomeGenBases[i].biomeID;
        }

        for (MapGenStructure mapgenstructure : this.structureGenerators)
        {
            mapgenstructure.func_151539_a(this, this.worldObj, chunkX, chunkZ, (Block[]) null);
        }

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
        Iterator iterator = this.structureGenerators.iterator();

        while (iterator.hasNext())
        {
            MapGenStructure mapgenstructure = (MapGenStructure) iterator.next();
            boolean flag1 = mapgenstructure.generateStructuresInChunk(this.worldObj, this.random, chunkX, chunkZ);

            if (mapgenstructure instanceof MapGenVillage)
            {
                flag |= flag1;
            }
        }

        int l1;
        int i2;
        int j2;

        if (this.waterLakeGenerator != null && !flag && this.random.nextInt(4) == 0)
        {
            l1 = k + this.random.nextInt(16) + 8;
            i2 = this.random.nextInt(256);
            j2 = l + this.random.nextInt(16) + 8;
            this.waterLakeGenerator.generate(this.worldObj, this.random, l1, i2, j2);
        }

        if (this.lavaLakeGenerator != null && !flag && this.random.nextInt(8) == 0)
        {
            l1 = k + this.random.nextInt(16) + 8;
            i2 = this.random.nextInt(this.random.nextInt(248) + 8);
            j2 = l + this.random.nextInt(16) + 8;

            if (i2 < 63 || this.random.nextInt(10) == 0)
            {
                this.lavaLakeGenerator.generate(this.worldObj, this.random, l1, i2, j2);
            }
        }

        if (this.hasDungeons)
        {
            for (l1 = 0; l1 < 8; ++l1)
            {
                i2 = k + this.random.nextInt(16) + 8;
                j2 = this.random.nextInt(256);
                int k1 = l + this.random.nextInt(16) + 8;
                (new WorldGenDungeons()).generate(this.worldObj, this.random, i2, j2, k1);
            }
        }

        if (this.hasDecoration)
        {
            biomegenbase.decorate(this.worldObj, this.random, k, l);
        }
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
        return "FlatLevelSource";
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
        if ("Stronghold".equals(p_147416_2_))
        {
            Iterator iterator = this.structureGenerators.iterator();

            while (iterator.hasNext())
            {
                MapGenStructure mapgenstructure = (MapGenStructure) iterator.next();

                if (mapgenstructure instanceof MapGenStronghold)
                {
                    return mapgenstructure.func_151545_a(p_147416_1_, p_147416_3_, p_147416_4_, p_147416_5_);
                }
            }
        }

        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(int par1, int par2)
    {
        Iterator iterator = this.structureGenerators.iterator();

        while (iterator.hasNext())
        {
            MapGenStructure mapgenstructure = (MapGenStructure) iterator.next();
            mapgenstructure.func_151539_a(this, this.worldObj, par1, par2, (Block[]) null);
        }
    }
}
