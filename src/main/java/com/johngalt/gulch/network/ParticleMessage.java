package com.johngalt.gulch.network;

import com.johngalt.gulch.effects.GaltEffects;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class ParticleMessage implements IMessage
{
    private double _X;
    private double _Y;
    private double _Z;
    private double _MotionX;
    private double _MotionY;
    private double _MotionZ;
    private GaltEffects.EffectEnum _ParticleType;

    public ParticleMessage()
    {
    }

    public ParticleMessage(GaltEffects.EffectEnum par1Effect, double par2X, double par4Y, double par6Z, double par8, double par10, double par12)
    {
        _X = par2X;
        _Y = par4Y;
        _Z = par6Z;
        _MotionX = par8;
        _MotionY = par10;
        _MotionZ = par12;
        _ParticleType = par1Effect;
    }

    public void SpawnParticle()
    {
        GaltEffects.AddEffectToWorld(_ParticleType, _X, _Y, _Z, _MotionX, _MotionY, _MotionZ);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

        _X = buf.readDouble();
        _Y = buf.readDouble();
        _Z = buf.readDouble();
        _MotionX = buf.readDouble();
        _MotionY = buf.readDouble();
        _MotionZ = buf.readDouble();
        _ParticleType = GaltEffects.EffectEnum.values()[buf.readInt()];

    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(_X);
        buf.writeDouble(_Y);
        buf.writeDouble(_Z);
        buf.writeDouble(_MotionX);
        buf.writeDouble(_MotionY);
        buf.writeDouble(_MotionZ);
        buf.writeInt(_ParticleType.ordinal());
    }
}
