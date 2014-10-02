package com.johngalt.gulch.tileentities;

import com.johngalt.gulch.blocks.common.GaltBlocks;
import com.johngalt.gulch.blocks.common.MultiblockDefinition;
import com.johngalt.gulch.tileentities.common.GaltTECustRenderHelper;
import com.johngalt.gulch.tileentities.common.GaltTECustRenderInterface;
import com.johngalt.gulch.tileentities.common.GaltTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ChatComponentText;

/**
 * Created on 7/5/2014.
 */
public class GaltMoldStationTE extends GaltTileEntity implements GaltTECustRenderInterface
{
    private GaltTECustRenderHelper _TERenderer;

    private MultiblockDefinition _DefinitionRel; //stores absolute values
    private int _TickCurrentCycle = 0;
    private boolean _MBComplete = false;

    public GaltMoldStationTE()
    {
        super();

        _TERenderer = new GaltTECustRenderHelper();

        _DefinitionRel = new MultiblockDefinition();
        _DefinitionRel.AddElement(0, 0, 1, GaltBlocks.ClayFurnaceBlock);
        _DefinitionRel.AddElement(1, 0, 1, GaltBlocks.BellowBlock);
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
        if (_TickCurrentCycle > 40)
        {
            _TickCurrentCycle = 0;

            MultiblockDefinition defAbs = _DefinitionRel.TranslateRelativeToAbsolute(this.xCoord, this.yCoord, this.zCoord); //absolute positions

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
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Multiblock completeness: " + complete));
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

}
