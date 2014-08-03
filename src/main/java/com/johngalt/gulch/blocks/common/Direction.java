package com.johngalt.gulch.blocks.common;

import net.minecraft.nbt.NBTTagCompound;

public class Direction
{
    public int multiplier;
    public boolean swapCoords;

    public Direction(int multiplier, boolean swapCoords)
    {
        this.multiplier = multiplier;
        this.swapCoords = swapCoords;
    }

    // rotates into various directions: North/South/West/East
    public Vector apply(Vector start, Vector ref)
    {
        Vector newVector = new Vector();

        if (!this.swapCoords)
        {
            newVector.x = start.x + ref.x * this.multiplier;
            newVector.y = start.y + ref.y;
            newVector.z = start.z + ref.z * this.multiplier;
        }
        else
        {
            // swapping delta x with delta z
            newVector.x = start.x + ref.z * this.multiplier;
            newVector.y = start.y + ref.y;
            newVector.z = start.z + ref.x * this.multiplier;
        }

        return newVector;
    }

    public static Direction North = new Direction(1, false);
    public static Direction South = new Direction(-1, false);
    public static Direction West = new Direction(1, true);
    public static Direction East = new Direction(-1, true);

    public static Direction[] AllDirections = new Direction[]{North, South, East, West};

    public void WriteToNBT(NBTTagCompound nbt)
    {
        nbt.setShort("multiplier", (short)multiplier);
        nbt.setBoolean("swapCoords", swapCoords);
    }

    public static Direction GetDirectionFromNBT(NBTTagCompound nbt)
    {
        return new Direction(nbt.getShort("multiplier"), nbt.getBoolean("swapCoords"));
    }

}
