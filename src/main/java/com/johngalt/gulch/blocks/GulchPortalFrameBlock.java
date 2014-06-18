package com.johngalt.gulch.blocks;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.lib.Strings;
import net.minecraft.block.Block;

/**
 * Created by on 6/17/2014.
 */
public class GulchPortalFrameBlock extends GaltCommonBlock
{
    public GulchPortalFrameBlock()
    {
        this.setBlockName(Strings.GulchPortalFrameBlockName);
        this.setHardness(1f);
        this.setResistance(3f);
        this.setCreativeTab(GulchMod.getCreativeTab());
        this.setStepSound(Block.soundTypeMetal);

        GaltBlocks.register(this);
    }
}
