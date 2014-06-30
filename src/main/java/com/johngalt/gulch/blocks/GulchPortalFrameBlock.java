package com.johngalt.gulch.blocks;

import com.johngalt.gulch.blocks.common.GaltBlocks;
import com.johngalt.gulch.blocks.common.GaltCommonBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Created by on 6/17/2014.
 */
public class GulchPortalFrameBlock extends GaltCommonBlock
{
    public GulchPortalFrameBlock()
    {
        super(Material.iron);
        this.setHardness(1f);
        this.setResistance(3f);
        this.setStepSound(Block.soundTypeMetal);
    }

    /*
    This will create a portal if the frame is complete: 4 by 6 hollow frame
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        // Any block in the frame may be the last to have been placed, so use a general logic to check the frame
        // Assume +Y is UP!

        FramePosition[] emptyFrame = {new FramePosition(0, 0, 0, GaltBlocks.portalFrame), new FramePosition(0, 1, 0, GaltBlocks.portalFrame), new FramePosition(0, 2, 0, GaltBlocks.portalFrame), new FramePosition(0, 3, 0, GaltBlocks.portalFrame), new FramePosition(0, 4, 0, GaltBlocks.portalFrame), new FramePosition(1, 0, 0, GaltBlocks.portalFrame), new FramePosition(1, 1, 0, Blocks.air), new FramePosition(1, 2, 0, Blocks.air), new FramePosition(1, 3, 0, Blocks.air), new FramePosition(1, 4, 0, GaltBlocks.portalFrame), new FramePosition(2, 0, 0, GaltBlocks.portalFrame), new FramePosition(2, 1, 0, Blocks.air), new FramePosition(2, 2, 0, Blocks.air), new FramePosition(2, 3, 0, Blocks.air), new FramePosition(2, 4, 0, GaltBlocks.portalFrame), new FramePosition(3, 0, 0, GaltBlocks.portalFrame), new FramePosition(3, 1, 0, GaltBlocks.portalFrame), new FramePosition(3, 2, 0, GaltBlocks.portalFrame), new FramePosition(3, 3, 0, GaltBlocks.portalFrame), new FramePosition(3, 4, 0, GaltBlocks.portalFrame)};

        for (int offsetx = -3; offsetx <= 3; offsetx++)
            for (int offsety = -4; offsety <= 4; offsety++)
                for (int offsetz = -3; offsetz <= 3; offsetz++)
                    if (isFrameCorrect(world, emptyFrame, x, y, z, offsetx, offsety, offsetz))
                    {
                        return;
                    }
    }

    /*
    Checks frame validity. Offset XYZ is the location of the current block in the frame you wish to check from.
     */
    public static boolean isFrameCorrect(World world, FramePosition[] frame, int x, int y, int z, int offsetX, int offsetY, int offsetZ)
    {
        boolean frameCorrect = true;
        for (FramePosition i : frame)
        {
            if (world.getBlock(x + i.x - offsetX, y + i.y - offsetY, z + i.z - offsetZ) != i.expected)
            {
                frameCorrect = false;
                break;
            }
        }

        if (frameCorrect)
        {
            if (world.getBlock(x + 1 - offsetX, y + 1 - offsetY, z - offsetZ) != GaltBlocks.portalBlock)
            {
                for (int dx = 1; dx <= 2; ++dx)
                    for (int dy = 1; dy <= 3; ++dy)
                        world.setBlock(x + dx - offsetX, y + dy - offsetY, z - offsetZ, GaltBlocks.portalBlock);
            }
            return frameCorrect;
        }

        // A frame can either be north to south OR west to east
        // Now check the other direction by swapping X with Z
        frameCorrect = true;
        for (FramePosition i : frame)
        {
            if (world.getBlock(x + i.z - offsetX, y + i.y - offsetY, z + i.x - offsetZ) != i.expected)
            {
                frameCorrect = false;
                return frameCorrect;
            }
        }

        if (frameCorrect)
        {
            if (world.getBlock(x + 1 - offsetX, y + 1 - offsetY, z - offsetZ) != GaltBlocks.portalBlock)
            {
                for (int dz = 1; dz <= 2; ++dz)
                    for (int dy = 1; dy <= 3; ++dy)
                        world.setBlock(x - offsetX, y + dy - offsetY, z + dz - offsetZ, GaltBlocks.portalBlock);
            }
        }

        return frameCorrect;
    }
}

/*
    Describes what to expect a given offset location in the world for a frame to be correct
 */
class FramePosition
{
    FramePosition(int x, int y, int z, Block block)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.expected = block;
    }

    public int x, y, z;
    public Block expected;
}
