package com.johngalt.gulch.blocks.common;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.lib.GaltLangGenerator;
import com.johngalt.gulch.lib.IGaltObject;
import com.johngalt.gulch.lib.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

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

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);


    }

    protected void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            Block bz1 = world.getBlock(x, y, z - 1);
            Block bzn1 = world.getBlock(x, y, z + 1);
            Block bx1 = world.getBlock(x - 1, y, z);
            Block bxn1 = world.getBlock(x + 1, y, z);

            byte facing = 3;

            // func_149730_j = isOpaque
            if (bz1.func_149730_j() && !bzn1.func_149730_j())
            {
                facing = 3;
            }
            else if (bzn1.func_149730_j() && !bz1.func_149730_j())
            {
                facing = 1;
            }
            else if (bx1.func_149730_j() && !bxn1.func_149730_j())
            {
                facing = 5;
            }
            else if (bxn1.func_149730_j() && !bx1.func_149730_j())
            {
                facing = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, facing, 2);
        }
    }
}
