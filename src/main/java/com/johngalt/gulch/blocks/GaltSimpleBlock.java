package com.johngalt.gulch.blocks;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.lib.Strings;
import net.minecraft.block.Block;

/**
 * Created on 6/14/2014.
 */
public class GaltSimpleBlock extends GaltCommonBlock
{
    public GaltSimpleBlock()
    {
        this.setBlockName(Strings.GaltSimpleBlockName);
        this.setHardness(1f);
        this.setResistance(3f);
        this.setCreativeTab(GulchMod.getCreativeTab());
        this.setStepSound(Block.soundTypeStone);

        GaltBlocks.register(this);
    }
}
