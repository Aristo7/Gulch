package com.johngalt.gulch.blocks;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.References;
import com.johngalt.gulch.lib.GaltLangGenerator;
import com.johngalt.gulch.lib.IGaltObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Created on 6/14/2014.
 */
public class GaltCommonBlock extends Block implements IGaltObject
{
    public GaltCommonBlock(Material material)
    {
        super(material);

    }

    public GaltCommonBlock()
    {
        this(Material.rock);

        this.setBlockName(GetGaltName());
        GaltLangGenerator.AddEntry(this);
        GaltBlocks.register(this);
        this.setCreativeTab(GulchMod.getCreativeTab());
    }

    public String getUnwrappedUnlocalizedName()
    {
        return super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1);
    }

    @Override
    public String getUnlocalizedName()
    {
        String tmp = String.format("tile.%s%s", References.RESOURCESPREFIX, getUnwrappedUnlocalizedName());
        return tmp;
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
