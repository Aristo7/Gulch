package com.johngalt.gulch.blocks.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Created by on 6/25/2014.
 */
public class GaltMultiBlock extends GaltCommonBlock
{
    private Definition[] description;

    /**
     * Constructor
     *
     * @param material
     */
    public GaltMultiBlock(Material material)
    {
        super(material);
    }

    /**
     * Must be called from
     *
     * @param elements list of block that define this multi block, can be rotated in 4 different way around +Y axis
     */
    public void describe(Definition[] elements)
    {
        description = elements;
    }

    public static class Definition
    {
        public Definition(int dx, int dy, int dz, Block block)
        {
            this.dx = dx;
            this.dy = dy;
            this.dz = dz;

            this.block = block;
            this.meta = 0;
        }

        public Definition(int dx, int dy, int dz, Block block, int meta)
        {
            this.dx = dx;
            this.dy = dy;
            this.dz = dz;

            this.block = block;
            this.meta = meta;
        }

        // local displacement from a logical center (0, 0, 0)
        public int dx, dy, dz;

        // What block should be at that displacement
        Block block;

        // and a specific metadata of that block
        int meta;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        // why is neighbor air???
        //if (!isBlockInList(neighbor)) return;

        if (isComplete(world, x, y, z, this.description[0], Direction.North))
        {
            markAllBlocks(world, x, y, z, this.description[0], Direction.North, 15);
        }
        else
        {
            markAllBlocks(world, x, y, z, this.description[0], Direction.North, 0);
        }
    }

    private void markAllBlocks(World world, int x, int y, int z, Definition ref, Direction dir, int newMeta)
    {
        int updateClientsFlag = 2;
        for (Definition d : this.description)
        {
            world.setBlock(
                    x + (d.dx - ref.dx) * dir.multiplierX,
                    y + (d.dy - ref.dy) * dir.multiplierY,
                    z + (d.dz - ref.dz) * dir.multiplierZ, d.block, newMeta, updateClientsFlag);
        }
    }

    private boolean isBlockInList(Block neighbor)
    {
        for (Definition d : this.description)
        {
            if (d.block == neighbor)
                return true;
        }

        return false;
    }

    /**
     * @param world
     * @param x
     * @param y
     * @param z
     * @param ref   a referencePoint, all definition points will be offset by ref
     * @param dir   north/west/east/south
     * @return
     */
    private boolean isComplete(World world, int x, int y, int z, Definition ref, Direction dir)
    {
        for (Definition d : this.description)
        {
            if (world.getBlock(
                    x + (d.dx - ref.dx) * dir.multiplierX,
                    y + (d.dy - ref.dy) * dir.multiplierY,
                    z + (d.dz - ref.dz) * dir.multiplierZ) != d.block)
            {
                return false;
            }
        }

        return true;
    }
}

class Direction
{
    public int multiplierX;
    public int multiplierY;
    public int multiplierZ;

    public Direction(int x, int y, int z)
    {
        this.multiplierX = x;
        this.multiplierY = y;
        this.multiplierZ = z;
    }

    public static Direction North = new Direction(1, 1, 1);
    public static Direction South = new Direction(-1, 1, 1);
    public static Direction West  = new Direction(1, 1, 1);
    public static Direction East  = new Direction(1, 1, -1);

    public static Direction[] AllDirections = new Direction[]{North, South, East, West};
}
