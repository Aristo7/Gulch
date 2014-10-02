package com.johngalt.gulch.blocks.common;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 10/2/2014.
 */
public class MultiblockDefinition
{
    private List<MultiblockDefElement> _Elements;

    public MultiblockDefinition ()
    {
        _Elements = new ArrayList<MultiblockDefElement>();
    }

    public void AddElement(int dx, int dy, int dz, Block block)
    {
        _Elements.add(new MultiblockDefElement(dx, dy, dz, block));
    }

    public MultiblockDefinition TranslateRelativeToAbsolute(int x, int y, int z)
    {
        MultiblockDefinition absMBD = new MultiblockDefinition();

        for (MultiblockDefElement element : _Elements)
        {
            absMBD.AddElement(element.TranslateX(x), element.TranslateY(y), element.TranslateZ(z), element.Block);
        }

        return absMBD;
    }

    public List<MultiblockDefElement> GetElements()
    {
        return _Elements;
    }

    public class MultiblockDefElement
    {
        public int DX;
        public int DY;
        public int DZ;
        public Block Block;

        public MultiblockDefElement(int dx, int dy, int dz, Block block)
        {
            DX = dx;
            DY = dy;
            DZ = dz;
            Block = block;
        }

        public int TranslateX(int x)
        {
            return x + DX;
        }

        public int TranslateY(int y)
        {
            return y + DY;
        }

        public int TranslateZ(int z)
        {
            return z + DZ;
        }
    }
}