package com.johngalt.gulch.tileentities.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * Created on 10/2/2014.
 */
public interface GaltTECustRenderInterface
{
    public void readFromNBT(NBTTagCompound nbt);

    public void writeToNBT(NBTTagCompound nbt);

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt);

    public Packet getDescriptionPacket();

    public GaltTECustRenderHelper GetTECustRenderHelper();

}
