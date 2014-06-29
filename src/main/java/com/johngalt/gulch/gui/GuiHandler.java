package com.johngalt.gulch.gui;

import com.johngalt.gulch.blocks.common.GaltMultiBlock;
import com.johngalt.gulch.blocks.common.MultiBlockManager;
import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class GuiHandler implements IGuiHandler
{
    public static int GuiTest = 0;
    public static int GuiWaterMultiBlock = 1;

    public GuiHandler()
    {
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GuiWaterMultiBlock)
        {
            // Create an Object of our TE, so we can give that to our inventory.

            TileEntity tileEntity = getEntityFromStructure(world, x, y, z);
            if (tileEntity == null)
            {
                GaltTileEntityContainer tileEntityTestContainer = (GaltTileEntityContainer) world.getTileEntity(x, y,
                        z);
                return new GaltInventoryContainer(player.inventory, tileEntityTestContainer);
            }
            else
            {
                if (tileEntity instanceof GaltTileEntityContainer)
                    return new GaltInventoryContainer(player.inventory, (GaltTileEntityContainer) tileEntity);
                else
                    return null;
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GuiTest)
        {
            return new GaltGUI();
        }
        else if (ID == GuiWaterMultiBlock)
        {
            // Create an Object of our TE, so we can give that to our GUI.

            TileEntity tileEntity = getEntityFromStructure(world, x, y, z);
            if (tileEntity == null)
            {
                GaltTileEntityContainer tileEntityTestContainer = (GaltTileEntityContainer) world.getTileEntity(x, y,
                        z);
                return new GuiInventory(player.inventory, tileEntityTestContainer);
            }
            else
            {
                if (tileEntity instanceof GaltTileEntityContainer)
                    return new GaltInventoryContainer(player.inventory, (GaltTileEntityContainer) tileEntity);
                else
                    return null;
            }
        }
        return null;
    }

    private TileEntity getEntityFromStructure(World world, int x, int y, int z)
    {
        MultiBlockManager.StructureInWorld structure = GaltMultiBlock.registrationManager.findStructure(world, x, y, z);
        if (structure != null)
        {
            return structure.commonEntity;
        }

        return null;
    }
}
