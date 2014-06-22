package com.johngalt.gulch.blocks;

import net.minecraft.block.Block;

/**
 * Created by on 6/21/2014.
 */
public class GaltRoadBlock extends GaltCommonBlock
{
    public GaltRoadBlock()
    {
        super();
        this.setHardness(1f);
        this.setResistance(3f);
        this.setStepSound(Block.soundTypeStone);
    }
}
