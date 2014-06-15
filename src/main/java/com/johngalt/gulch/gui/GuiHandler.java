package com.johngalt.gulch.gui;

import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class GuiHandler implements IGuiHandler
{
    public GuiHandler()
    {
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == 1)
        {
            // Create an Object of our TE, so we can give that to our inventory.
            GaltTileEntityContainer tileEntityTestContainer = (GaltTileEntityContainer) world.getTileEntity(x, y, z);
            return new GaltInventoryContainer(player.inventory, tileEntityTestContainer);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == 0) return new GaltGUI();
        else if (ID == 1)
        {
            // Create an Object of our TE, so we can give that to our GUI.
            GaltTileEntityContainer tileEntityTestContainer = (GaltTileEntityContainer) world.getTileEntity(x, y, z);
            return new GuiInventory(player.inventory, tileEntityTestContainer);
        }
        return null;
    }
}
