package com.johngalt.gulch.blocks.common;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.lib.GaltLangGenerator;
import com.johngalt.gulch.lib.IGaltObject;
import com.johngalt.gulch.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Created on 6/14/2014.
 */
public abstract class GaltCommonBlockContainer extends BlockContainer implements IGaltObject
{
    protected GaltCommonBlockContainer(Material material)
    {
        super(material);
        initializeContainer(GetGaltName());
    }

    protected GaltCommonBlockContainer(Material material, boolean customInitialization)
    {
        super(material);

        if (customInitialization)
        {
            initializeContainer(GetGaltName());
        }
    }

    protected void initializeContainer(String customBlockName)
    {
        this.setBlockName(customBlockName);
        GaltBlocks.register(this);
        GaltLangGenerator.AddEntry(this);
        this.setCreativeTab(GulchMod.getCreativeTab());
    }

    public String getUnwrappedUnlocalizedName()
    {
        return super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", References.RESOURCESPREFIX, getUnwrappedUnlocalizedName());
    }

    @Override
    public String GetGaltName()
    {
        return this.getClass().getSimpleName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(References.RESOURCESPREFIX + getUnwrappedUnlocalizedName());
    }
}
