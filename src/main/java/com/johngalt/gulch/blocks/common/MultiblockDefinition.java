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

    public MultiblockDefinition TranslateRelativeToAbsolute(int x, int y, int z, short direction)
    {
        MultiblockDefinition absMBD = new MultiblockDefinition();

        for (MultiblockDefElement element : _Elements)
        {
            absMBD.AddElement(element.TranslateX(x, direction), element.TranslateY(y, direction), element.TranslateZ(z, direction), element.Block);
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

        public int TranslateX(int x, short direction)
        {
            int xMod;

            switch (direction)
            {
                case 0:
                    xMod = -DX;
                    break;
                case 2:
                    xMod = DX;
                    break;
                case 1:
                    xMod = -DZ;
                    break;
                case 3:
                    xMod = DZ;
                    break;
                default:
                    xMod = DX;
                    break;
            }

            return x + xMod;
        }

        public int TranslateY(int y, short direction)
        {
            return y + DY;
        }

        public int TranslateZ(int z, short direction)
        {
            int zMod;

            switch (direction)
            {
                case 0:
                    zMod = DZ;
                    break;
                case 2:
                    zMod = -DZ;
                    break;
                case 1:
                    zMod = -DX;
                    break;
                case 3:
                    zMod = DX;
                    break;
                default:
                    zMod = DZ;
                    break;
            }

            return z + zMod;
        }
    }
}