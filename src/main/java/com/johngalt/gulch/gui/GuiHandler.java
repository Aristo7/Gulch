package com.johngalt.gulch.gui;

import com.johngalt.gulch.blocks.common.GaltMultiBlockBlock;
import com.johngalt.gulch.blocks.common.MultiBlockManager;
import com.johngalt.gulch.tileentities.common.GaltTileEntityContainer;
import com.johngalt.gulch.tileentities.common.GaltTileEntityMachine;
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

    public static final int GuiTest = 0;
    public static final int GuiWaterMultiBlock = 1;

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
                case GUI_ID_MACHINEBLOCK:
                    if (tileentity instanceof GaltTileEntityMachine)
                        return ((GaltTileEntityMachine) tileentity).GetContainer(player.inventory); //new GaltContainerMachine(player.inventory, (GaltTileEntityMachine) tileentity);
                case GuiWaterMultiBlock:
                    // Create an Object of our TE, so we can give that to our inventory.

                    TileEntity tileEntity = getEntityFromStructure(world, x, y, z);
                    if (tileEntity == null)
                    {
                        GaltTileEntityContainer tileEntityTestContainer = (GaltTileEntityContainer) world.getTileEntity(x, y, z);
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
                case GUI_ID_MACHINEBLOCK:
                    if (tileentity instanceof GaltTileEntityMachine)
                        return ((GaltTileEntityMachine) tileentity).CreateGUIInstance(player.inventory);
                case GuiWaterMultiBlock:
                    // Create an Object of our TE, so we can give that to our GUI.

                    TileEntity tileEntity = getEntityFromStructure(world, x, y, z);
                    if (tileEntity == null)
                    {
                        GaltTileEntityContainer tileEntityTestContainer = (GaltTileEntityContainer) world.getTileEntity(x, y, z);
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
        }
        return null;
    }

    private TileEntity getEntityFromStructure(World world, int x, int y, int z)
    {
        MultiBlockManager.StructureInWorld structure = GaltMultiBlockBlock.registrationManager.findStructure(world, x, y, z);
        if (structure != null)
        {
            return structure.commonEntity;
        }

        return null;
    }
}
