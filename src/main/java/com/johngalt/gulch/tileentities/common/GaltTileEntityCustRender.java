package com.johngalt.gulch.tileentities.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * Created on 7/1/2014.
 */
public class GaltTileEntityCustRender extends GaltTileEntity
{
    private short _Direction;

    public GaltTileEntityCustRender()
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

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        _Direction = nbt.getShort("direction");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setShort("direction", _Direction);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tagCom = new NBTTagCompound();
        this.writeToNBT(tagCom);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tagCom);
    }

}