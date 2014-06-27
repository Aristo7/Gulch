package com.johngalt.gulch.blocks.common;

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

    public void registerStructure(World world, ArrayList<GaltMultiBlock.Definition> registration)
    {
        multiblocks.add(new StructureInWorld(world, registration));
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
        StructureInWorld removeThis = null;

        for (StructureInWorld structure : this.multiblocks)
        {
            if (structure.world != world) continue;

            boolean removeMe = false;

            for (GaltMultiBlock.Definition def : structure.definition)
            {
                if (def.dx == x && def.dy == y && def.dz == z)
                {
                    // this is the one to remove!
                    removeMe = true;
                    break;
                }
            }

            if (removeMe)
            {
                for (GaltMultiBlock.Definition def : structure.definition)
                {
                    if (world.getBlock(def.dx, def.dy, def.dz) == def.block)
                    {
                        world.setBlock(def.dx, def.dy, def.dz, def.block, 0, GaltMultiBlock.updateClientsFlag);
                    }
                }

                removeThis = structure;
                break; // all done here
            }
        }

        if (removeThis != null)
        {
            this.multiblocks.remove(removeThis);
        }
    }
}

class StructureInWorld
{
    public StructureInWorld(World world, ArrayList<GaltMultiBlock.Definition> definition)
    {
        this.world = world;
        this.definition = definition;
    }

    public ArrayList<GaltMultiBlock.Definition> definition;
    public World world;
}
