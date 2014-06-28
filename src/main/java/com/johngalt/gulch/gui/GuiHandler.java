package com.johngalt.gulch.gui;

import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import com.johngalt.gulch.tileentities.GaltTileEntityMachine;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class GuiHandler implements IGuiHandler
{
    public static final int GUI_ID_MACHINEBLOCK = 3;

    public GuiHandler()
    {
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileentity = world.getTileEntity(x, y, z);
        if (tileentity != null)
        {
            switch (ID)
            {
                case 1:
                    // Create an Object of our TE, so we can give that to our inventory.
                    GaltTileEntityContainer tileEntityTestContainer = (GaltTileEntityContainer) world.getTileEntity(x, y, z);
                    return new GaltInventoryContainer(player.inventory, tileEntityTestContainer);
                case GUI_ID_MACHINEBLOCK:
                    if (tileentity instanceof GaltTileEntityMachine)
                        return new GaltContainerMachine(player.inventory, (GaltTileEntityMachine) tileentity);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileentity = world.getTileEntity(x, y, z);
        if (tileentity != null)
        {
            switch (ID)
            {
                case 0:
                    return new GaltGUI();
                case 1:
                    if (tileentity instanceof GaltTileEntityContainer)
                        return new GuiInventory(player.inventory, (GaltTileEntityContainer) tileentity);
                case GUI_ID_MACHINEBLOCK:
                    if (tileentity instanceof GaltTileEntityMachine)
                        return new GaltGuiMachine(player.inventory, (GaltTileEntityMachine) tileentity);
            }
        }
        return null;
    }
}
