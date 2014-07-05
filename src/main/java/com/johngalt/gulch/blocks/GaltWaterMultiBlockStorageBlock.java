package com.johngalt.gulch.blocks;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.blocks.common.GaltMultiBlockBlock;
import com.johngalt.gulch.blocks.common.MultiBlockManager;
import com.johngalt.gulch.gui.GuiHandler;
import com.johngalt.gulch.lib.References;
import com.johngalt.gulch.tileentities.common.GaltTileEntityContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by on 6/25/2014.
 */
public class GaltWaterMultiBlockStorageBlock extends GaltMultiBlockBlock
{
    private IIcon structureCompleteIcon;
    private int metaNotActivated = 0;
    private int metaActivated = 15;

    public GaltWaterMultiBlockStorageBlock()
    {
        super(Material.rock);

        describe(new Definition[]{
                new Definition(0, 0, 0, this),
                new Definition(0, 1, 0, this),
                new Definition(1, 1, 0, this),
                new Definition(-1, 1, 0, this),
                new Definition(1, 0, 0, this),
                new Definition(-1, 0, 0, this),
                new Definition(0, 0, 1, this)});
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta == metaActivated)
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

    @Override
    public boolean hasTileEntity(int meta)
    {
        // doesn't have GUI until the structure is set
        if (meta == metaActivated)
            return true;
        else
            return false;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int meta)
    {
        if (meta == metaActivated)
            return new GaltTileEntityContainer();
        else
            return null;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par1, float par2, float par3, float par4)
    {
        if (world.getBlockMetadata(x, y, z) == metaActivated)
        {
            MultiBlockManager.StructureInWorld structure = GaltMultiBlockBlock.registrationManager.findStructure(world, x, y, z);
            if (structure != null)
            {
                entityPlayer.openGui(GulchMod.instance, GuiHandler.GuiWaterMultiBlock, world, x, y, z);
                return true;
            }
        }

        return false;
    }
}
