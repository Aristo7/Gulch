package com.johngalt.gulch.blocks.common;

public class Vector
{
    public Vector()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int x, y, z;
}
