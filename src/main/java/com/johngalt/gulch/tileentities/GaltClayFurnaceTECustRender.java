package com.johngalt.gulch.tileentities;


import com.johngalt.gulch.tileentities.common.GaltTECustRenderHelper;
import com.johngalt.gulch.tileentities.common.GaltTECustRenderInterface;
import com.johngalt.gulch.tileentities.common.GaltTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * Created on 7/4/2014.
 */
public class GaltClayFurnaceTECustRender extends GaltTileEntity implements GaltTECustRenderInterface
{
    private GaltTECustRenderHelper _TERenderer;

    public GaltClayFurnaceTECustRender()
    {
        super();

        _TERenderer = new GaltTECustRenderHelper();
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
}
