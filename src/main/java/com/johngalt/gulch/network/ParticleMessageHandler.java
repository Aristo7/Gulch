package com.johngalt.gulch.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ParticleMessageHandler implements IMessageHandler<ParticleMessage, IMessage>
{
    @Override
    public IMessage onMessage(ParticleMessage message, MessageContext ctx)
    {
        message.SpawnParticle();
        return null;
    }
}
