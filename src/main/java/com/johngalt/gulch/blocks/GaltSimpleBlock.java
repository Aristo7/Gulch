package com.johngalt.gulch.blocks;

import net.minecraft.block.Block;

/**
 * Created on 6/14/2014.
 */
public class GaltSimpleBlock extends GaltCommonBlock
{
    public GaltSimpleBlock()
    {
        super();
        this.setHardness(1f);
        this.setResistance(3f);
        this.setStepSound(Block.soundTypeStone);

    }
}
