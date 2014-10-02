package com.johngalt.gulch.tileentities.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * Created on 7/1/2014.
 */
public class GaltTECustRenderHelper
{
    private short _Direction;

    public GaltTECustRenderHelper()
    {
        super();
    }

    public void SetDirection(short direction)
    {
        _Direction = direction;
    }

    public short GetDirection()
    {
        return _Direction;
    }

    //@Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        _Direction = nbt.getShort("direction");
    }

    //@Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setShort("direction", _Direction);
    }

    //@Override
    public static void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt, GaltTileEntity te)
    {
        te.readFromNBT(pkt.func_148857_g());
    }

    //@Override
    public static Packet getDescriptionPacket(GaltTileEntity te)
    {
        NBTTagCompound tagCom = new NBTTagCompound();
        te.writeToNBT(tagCom);
        return new S35PacketUpdateTileEntity(te.xCoord, te.yCoord, te.zCoord, te.blockMetadata, tagCom);
    }

}