package com.johngalt.gulch.blocks.common;

import com.johngalt.gulch.lib.Position;
import com.johngalt.gulch.tileentities.GaltMultiblockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by on 6/25/2014.
 */
public abstract class GaltMultiBlockBlock extends GaltCommonBlockContainer
{
    private Definition[] description;
    private long _Tick;

    public static int updateClientsFlag = 2;
    //public static MultiBlockManager registrationManager = new MultiBlockManager();

    /**
     * Constructor
     *
     * @param material
     */
    public GaltMultiBlockBlock(Material material)
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
        }

        public Definition(int dx, int dy, int dz, Block block, int meta)
        {
            this.dx = dx;
            this.dy = dy;
            this.dz = dz;

            this.block = block;
        }

        // local displacement from a logical center (0, 0, 0)
        public int dx, dy, dz;

        // What block should be at that displacement
        Block block;


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
        boolean isComplete;
        Direction foundDirection = null;
        Definition foundOriginPoint = null;

        // pull from entity if complete
        TileEntity etest = world.getTileEntity(x, y, z);
        if (etest instanceof GaltMultiblockTileEntity)
        {
            GaltMultiblockTileEntity entity = (GaltMultiblockTileEntity) etest;
            isComplete = entity.IsComplete();

            if (isComplete)
            {
                foundDirection = entity.GetDirection();
                foundOriginPoint = entity.GetOriginDefinition();
            }


            // if it is complete, check to see if it is still structurally sound
            if (isComplete)
            {
                if (!isComplete(world, x, y, z, foundOriginPoint, foundDirection))
                {
                    isComplete = false;
                }
            }
            else // if not complete, see if it is now
            {
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
            }


            // if it is complete but not set as such yet, then mark it as complete
            if (_Tick != world.getWorldTime()) // Only perform this once per a place
            {
                _Tick = world.getWorldTime();
                if (isComplete && !entity.IsComplete())
                {
                    markAllBlocks(world, x, y, z, foundOriginPoint, foundDirection, true);
                }
                else if (!isComplete && entity.IsComplete())
                {

                    markAllBlocks(world, x, y, z, foundOriginPoint, foundDirection, false);
                }
            }
        }
    }

    private void markAllBlocks(World world, int x, int y, int z, Definition ref, Direction dir, boolean completed)
    {
        // set the origin tile entity
        Position originPos = new Position(x - ref.dx, y - ref.dy, z - ref.dz);
        TileEntity entity = world.getTileEntity(originPos.x, originPos.y, originPos.z);
        if (entity instanceof GaltMultiblockTileEntity)
        {
            GaltMultiblockTileEntity originMBEntity = (GaltMultiblockTileEntity) entity;

            // Set origin
            if (completed)
            {
                originMBEntity.SetOrigin(originPos, dir, true);
                originMBEntity.SetCompleted(true);

            }
            else
            {
                originMBEntity.SetCompleted(false);
            }
        }

        // set the other tiles as complete/incomplete
        for (Definition d : this.description)
        {
            Vector result = dir.apply(new Vector(x, y, z), new Vector(d.dx - ref.dx, d.dy - ref.dy, d.dz - ref.dz));

            entity = world.getTileEntity(result.x, result.y, result.z);
            if (entity instanceof GaltMultiblockTileEntity)
            {
                GaltMultiblockTileEntity mbEntity = (GaltMultiblockTileEntity) entity;
                if (mbEntity.IsComplete() != completed)
                {
                    if (completed)
                    {
                        mbEntity.SetOrigin(originPos, dir, false);
                        mbEntity.SetCompleted(true);
                    }
                    else
                    {
                        mbEntity.ResetOriginValues();
                        mbEntity.SetCompleted(false);
                    }
                }
            }
        }
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

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new GaltMultiblockTileEntity();
    }

}

