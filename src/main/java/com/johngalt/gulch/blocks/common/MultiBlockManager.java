package com.johngalt.gulch.blocks.common;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by on 6/26/2014.
 */
public class MultiBlockManager
{
    private ArrayList<StructureInWorld> multiblocks;

    public MultiBlockManager()
    {
        multiblocks = new ArrayList<StructureInWorld>();
    }

    public void registerStructure(World world, ArrayList<GaltMultiBlock.Definition> registration, TileEntity entity)
    {
        multiblocks.add(new StructureInWorld(world, registration, entity));
    }

    /**
     * Find a structure at XYZ and sets all its still existing blocks to metadata zero
     *
     * @param world
     * @param x
     * @param y
     * @param z
     */
    public void findAndRemoveStructure(World world, int x, int y, int z)
    {
        StructureInWorld removeThis = findStructure(world, x, y, z);

        if (removeThis != null)
        {
            for (GaltMultiBlock.Definition def : removeThis.definition)
            {
                if (world.getBlock(def.dx, def.dy, def.dz) == def.block)
                {
                    world.setBlock(def.dx, def.dy, def.dz, def.block, 0, GaltMultiBlock.updateClientsFlag);
                }
            }

            this.multiblocks.remove(removeThis);
        }
    }

    public StructureInWorld findStructure(World world, int x, int y, int z)
    {
        for (StructureInWorld structure : this.multiblocks)
        {
            if (structure.world != world) continue;

            for (GaltMultiBlock.Definition def : structure.definition)
            {
                if (def.dx == x && def.dy == y && def.dz == z)
                {
                    // this is the one!
                    return structure;
                }
            }
        }

        return null;
    }

    public static class StructureInWorld
    {
        public StructureInWorld(World world, ArrayList<GaltMultiBlock.Definition> definition, TileEntity entity)
        {
            this.world = world;
            this.definition = definition;
            this.commonEntity = entity;
        }

        public ArrayList<GaltMultiBlock.Definition> definition;
        public World world;
        public TileEntity commonEntity;
    }
}
