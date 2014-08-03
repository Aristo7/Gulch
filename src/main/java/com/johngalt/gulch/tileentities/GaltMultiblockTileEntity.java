package com.johngalt.gulch.tileentities;


import com.johngalt.gulch.blocks.common.Direction;
import com.johngalt.gulch.blocks.common.GaltMultiBlockBlock;
import com.johngalt.gulch.lib.Position;
import com.johngalt.gulch.tileentities.common.GaltTileEntity;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created on 7/6/2014.
 */
public class GaltMultiblockTileEntity extends GaltTileEntity
{
    private Position _MBOrigin;
    private Direction _MBDirection;

    private boolean _IsOrigin = false;
    private boolean _IsComplete = false;

    public GaltMultiblockTileEntity()
    {
    }

    public void SetOrigin(Position posOrigin, Direction dir, boolean isOrigin)
    {
        _MBOrigin = posOrigin;
        _MBDirection = dir;
        _IsOrigin = isOrigin;

    }

    public void SetCompleted(boolean completed)
    {
        _IsComplete = completed;

        if (completed)
            this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 1, 2);
        else
            this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 2);


        if (!completed && _IsOrigin)
        {
            _MBOrigin = null;
            _MBDirection = null;
        }
    }


    public boolean IsComplete()
    {
        return _IsComplete;
    }


    public Direction GetDirection()
    {
        return _MBDirection;
    }

    public GaltMultiBlockBlock.Definition GetOriginDefinition()
    {
        if (_MBOrigin != null)
            return new GaltMultiBlockBlock.Definition(this.xCoord - _MBOrigin.x, this.yCoord - _MBOrigin.y, this.zCoord - _MBOrigin.z, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
        else
            return null;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        _IsComplete = nbt.getBoolean("IsComplete");
        _IsOrigin = nbt.getBoolean("IsOrigin");

        if (nbt.hasKey("Direction"))
        {
            _MBDirection = Direction.GetDirectionFromNBT(nbt.getCompoundTag("Direction"));
        }

        if (nbt.hasKey("Origin"))
        {
            _MBOrigin = Position.GetPositionFromNBT(nbt.getCompoundTag("Origin"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setBoolean("IsOrigin", _IsOrigin);
        nbt.setBoolean("IsComplete", _IsComplete);

        if (_MBDirection != null)
        {
            NBTTagCompound direction = new NBTTagCompound();
            _MBDirection.WriteToNBT(direction);
            nbt.setTag("Direction", direction);
        }

        if (_MBOrigin != null)
        {
            NBTTagCompound direction = new NBTTagCompound();
            _MBOrigin.WriteToNBT(direction);
            nbt.setTag("Origin", direction);
        }
    }

    public void ResetOriginValues()
    {
        _MBOrigin = null;
        _MBDirection = null;
        _IsOrigin = false;
    }
}
