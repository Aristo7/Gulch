package com.johngalt.gulch.blocks.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by on 6/25/2014.
 */
public abstract class GaltMultiBlock extends GaltCommonContainer
{
    private Definition[] description;

    public static int               updateClientsFlag   = 2;
    public static MultiBlockManager registrationManager = new MultiBlockManager();

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

    /**
     * @param world
     * @param x
     * @param y
     * @param z
     * @param neighbor this is the block that was removed!
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        boolean isComplete = false;
        Direction foundDirection = null;
        Definition foundOriginPoint = null;

        for (Direction dir : Direction.AllDirections)
        {
            for (Definition def : this.description)
            {
                if (isComplete(world, x, y, z, def, dir))
                {
                    isComplete = true;
                    foundDirection = dir;
                    foundOriginPoint = def;
                    break;
                }
            }

            if (isComplete)
                break;
        }

        if (isComplete)
        {
            markAllBlocks(world, x, y, z, foundOriginPoint, foundDirection, 15);
        }
        else
        {
            // reset back to default metadata - the structure is not complete
            registrationManager.findAndRemoveStructure(world, x, y, z);
        }
    }

    private void markAllBlocks(World world, int x, int y, int z, Definition ref, Direction dir, int newMeta)
    {
        ArrayList<Definition> registration = new ArrayList<Definition>();
        boolean isThisNewStructure = false;

        for (Definition d : this.description)
        {
            Vector result = dir.apply(new Vector(x, y, z), new Vector(d.dx - ref.dx, d.dy - ref.dy, d.dz - ref.dz));

            if (world.getBlock(result.x, result.y, result.z) == d.block)
            {
                if (world.getBlockMetadata(result.x, result.y, result.z) != newMeta)
                {
                    world.setBlock(result.x, result.y, result.z, d.block, newMeta, updateClientsFlag);
                    registration.add(new Definition(result.x, result.y, result.z, d.block));

                    isThisNewStructure = true;
                }
            }
        }

        if (isThisNewStructure)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            assert tile != null;
            registrationManager.registerStructure(world, registration, tile);
        }
    }

    /**
     * Override to customize
     *
     * @return
     */
    public TileEntity getCommonTileEntity()
    {
        return null;
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
            Vector result = dir.apply(new Vector(x, y, z), new Vector(d.dx - ref.dx, d.dy - ref.dy, d.dz - ref.dz));

            // check both block and its expected meta
            if (world.getBlock(result.x, result.y, result.z) != d.block)
            {
                return false;
            }
        }

        return true;
    }
}

class Direction
{
    public int     multiplier;
    public boolean swapCoords;

    public Direction(int multiplier, boolean swapCoords)
    {
        this.multiplier = multiplier;
        this.swapCoords = swapCoords;
    }

    // rotates into various directions: North/South/West/East
    public Vector apply(Vector start, Vector ref)
    {
        Vector newVector = new Vector();

        if (this.swapCoords == false)
        {
            newVector.x = start.x + ref.x * this.multiplier;
            newVector.y = start.y + ref.y;
            newVector.z = start.z + ref.z * this.multiplier;
        }
        else
        {
            // swapping delta x with delta z
            newVector.x = start.x + ref.z * this.multiplier;
            newVector.y = start.y + ref.y;
            newVector.z = start.z + ref.x * this.multiplier;
        }

        return newVector;
    }

    public static Direction North = new Direction(1, false);
    public static Direction South = new Direction(-1, false);
    public static Direction West  = new Direction(1, true);
    public static Direction East  = new Direction(-1, true);

    public static Direction[] AllDirections = new Direction[]{North, South, East, West};
}

class Vector
{
    public Vector()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int x, y, z;
}