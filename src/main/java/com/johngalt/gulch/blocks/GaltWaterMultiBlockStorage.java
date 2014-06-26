package com.johngalt.gulch.blocks;

import com.johngalt.gulch.blocks.common.GaltMultiBlock;
import com.johngalt.gulch.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by on 6/25/2014.
 */
public class GaltWaterMultiBlockStorage extends GaltMultiBlock
{
    private IIcon structureCompleteIcon;

    public GaltWaterMultiBlockStorage()
    {
        super(Material.rock);

        /*
        describe(new Definition[]{
                new Definition(0, 0, 0, this),
                new Definition(0, 1, 0, this),
                new Definition(1, 1, 0, this),
                new Definition(-1, 1, 0, this),
                new Definition(1, 0, 0, this),
                new Definition(-1, 0, 0, this),
                new Definition(0, 0, 1, this)});
                */

        describe(new Definition[]{
                new Definition(0, 0, 0, this),
                new Definition(0, 1, 0, this)});
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 15)
            return this.structureCompleteIcon;
        else
            return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        super.registerBlockIcons(iconRegister);

        this.structureCompleteIcon = iconRegister.registerIcon(References.RESOURCESPREFIX + getUnwrappedUnlocalizedName() + "completed");
    }
}
