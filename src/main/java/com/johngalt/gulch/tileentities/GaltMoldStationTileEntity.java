package com.johngalt.gulch.tileentities;

import com.johngalt.gulch.blocks.common.GaltBlocks;
import com.johngalt.gulch.blocks.common.GaltMachineBlock;
import com.johngalt.gulch.blocks.common.MultiblockDefinition;
import com.johngalt.gulch.items.GaltCommonItem;
import com.johngalt.gulch.items.GaltItems;
import com.johngalt.gulch.items.ItemMold;
import com.johngalt.gulch.tileentities.common.GaltTECustRenderHelper;
import com.johngalt.gulch.tileentities.common.GaltTECustRenderInterface;
import com.johngalt.gulch.tileentities.common.GaltTileEntityMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ChatComponentText;

/**
 * Created on 7/5/2014.
 */
public class GaltMoldStationTileEntity extends GaltTileEntityMachine implements GaltTECustRenderInterface
{
    private GaltTECustRenderHelper _TERenderer;

    private MultiblockDefinition _DefinitionRel; //stores absolute values
    private int _TickCurrentCycle = 0;
    private boolean _MBComplete = false;
    private short _Direction = 0;


    public GaltMoldStationTileEntity()
    {
        super(GaltBlocks.MoldStationBlock, GaltBlocks.MoldStationBlock);

        // custom renderer code
        _TERenderer = new GaltTECustRenderHelper();

        // multblock stuff
        _DefinitionRel = new MultiblockDefinition();
        _DefinitionRel.AddElement(0, 0, 1, GaltBlocks.ClayFurnaceBlock);
        _DefinitionRel.AddElement(1, 0, 1, GaltBlocks.BellowBlock);

        // Machine code
        this.Slots.add(new GaltTileEntityMachine.MachineSlot(0, GaltTileEntityMachine.ComponentType.Input, 56, 35, new ItemStack[]{new ItemStack(GaltItems.IngotLead), new ItemStack(GaltItems.MusketShotMold)}));
        this.Slots.add(new GaltTileEntityMachine.MachineSlot(3, ComponentType.RequiredItems, 84, 60, new ItemStack[]{new ItemStack(GaltItems.MusketShotMold)}));
        this.Slots.add(new GaltTileEntityMachine.MachineSlot(1, GaltTileEntityMachine.ComponentType.Fuel, 8, 62));
        this.Slots.add(new GaltTileEntityMachine.MachineSlot(2, GaltTileEntityMachine.ComponentType.Output, 116, 35));

        addMoldAndProductRecipe(GaltItems.MusketShotMold, GaltItems.MusketShotMoldFired, GaltItems.IngotLead, GaltItems.MusketShot, 16);
        addMoldAndProductRecipe(GaltItems.MusketBarrelMold, GaltItems.MusketBarrelMoldFired, Items.iron_ingot, GaltItems.MusketBarrel, 1);
        addMoldAndProductRecipe(GaltItems.MusketTriggerMold, GaltItems.MusketTriggerMoldFired, Items.iron_ingot, GaltItems.MusketTrigger, 1);
        addMoldAndProductRecipe(GaltItems.MusketRamRodMold, GaltItems.MusketRamRodMoldFired, Items.iron_ingot, GaltItems.MusketRamRod, 1);
        addMoldAndProductRecipe(GaltItems.MusketHammerMold, GaltItems.MusketHammerMoldFired, Items.iron_ingot, GaltItems.MusketHammer, 1);

        addMoldAndProductRecipe(GaltItems.RevolverShotMold, GaltItems.RevolverShotMoldFired, GaltItems.IngotLead, GaltItems.RevolverShot, 16);
        addMoldAndProductRecipe(GaltItems.BasicRevolverBarrelMold, GaltItems.BasicRevolverBarrelMoldFired, Items.iron_ingot, GaltItems.BasicRevolverBarrel, 1);
        addMoldAndProductRecipe(GaltItems.BasicRevolverCartidgeMold, GaltItems.BasicRevolverCartidgeMoldFired, Items.iron_ingot, GaltItems.BasicRevolverCartridge, 1);
        addMoldAndProductRecipe(GaltItems.BasicRevolverHammerMold, GaltItems.BasicRevolverHammerMoldFired, Items.iron_ingot, GaltItems.BasicRevolverHammer, 1);
        addMoldAndProductRecipe(GaltItems.BasicRevolverTriggerMold, GaltItems.BasicRevolverTriggerMoldFired, Items.iron_ingot, GaltItems.BasicRevolverTrigger, 1);
        addMoldAndProductRecipe(GaltItems.BasicRevolverInnerRodMold, GaltItems.BasicRevolverInnerRodMoldFired, Items.iron_ingot, GaltItems.BasicRevolverInnerRod, 1);

        // shit forgot inner rod

        this.RecipeList.AddFuel(new ItemStack(GaltItems.GunPowder, 1), 100);

        this.AllowRegisteredFuel();

        this.setGuiDisplayName("Molding Station");


    }

    private void addMoldAndProductRecipe(ItemMold mold, ItemMold firedMold, Item rawMaterial, GaltCommonItem finalProduct, int numOutput)
    {
        this.RecipeList.AddRecipe(
                new ItemStack[]{new ItemStack(mold, 1)},
                null,
                null,
                new ItemStack[]{new ItemStack(firedMold, 1)}
        );

        this.RecipeList.AddRecipe(
                new ItemStack[]{new ItemStack(rawMaterial, 1)},
                null,
                new ItemStack[]{new ItemStack(firedMold)},
                new ItemStack[]{new ItemStack(finalProduct, numOutput)}
        );
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        _TERenderer.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        _TERenderer.writeToNBT(nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        GaltTECustRenderHelper.onDataPacket(net, pkt, this);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return GaltTECustRenderHelper.getDescriptionPacket(this);
    }

    @Override
    public GaltTECustRenderHelper GetTECustRenderHelper()
    {
        return _TERenderer;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (_TickCurrentCycle > 40)
        {
            _TickCurrentCycle = 0;

            MultiblockDefinition defAbs = _DefinitionRel.TranslateRelativeToAbsolute(this.xCoord, this.yCoord, this.zCoord, _Direction); //absolute positions

            boolean complete = true;

            for (MultiblockDefinition.MultiblockDefElement element : defAbs.GetElements())
            {
                if (!this.worldObj.getBlock(element.DX, element.DY, element.DZ).equals(element.Block))
                {
                    complete = false;
                }
            }

            if (_MBComplete != complete)
            {
                _MBComplete = complete;
                onMBCompletenessChange();

                if (!this.worldObj.isRemote)
                {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Multiblock completeness: " + complete));
                }
            }
        }
        else
        {
            _TickCurrentCycle++;
        }
    }

    private void onMBCompletenessChange()
    {

    }

    public boolean IsMBComplete()
    {
        return _MBComplete;
    }


    public void SetDirection(short dir)
    {
        _Direction = dir;
    }

    @Override
    protected void setActiveInactiveBlocks(GaltMachineBlock activeBlock, GaltMachineBlock inactiveBlock)
    {

    }
}
