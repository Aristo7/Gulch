package com.johngalt.gulch.blocks;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.gui.GuiHandler;
import com.johngalt.gulch.lib.References;
import com.johngalt.gulch.tileentities.GaltTileEntity;
import com.johngalt.gulch.tileentities.GaltTileEntityMachine;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created on 6/27/2014.
 */
public abstract class GaltMachineBlock extends GaltCommonContainer
{
    private final boolean _IsActive;
    private static boolean _KeepInventory;

    private int _GuiID;

    private final Random _Random = new Random();

    @SideOnly(Side.CLIENT)
    private IIcon _IconFront;
    @SideOnly(Side.CLIENT)
    private IIcon _IconTop;
    @SideOnly(Side.CLIENT)
    private IIcon _IconBottom;

    public GaltMachineBlock(boolean isActive, int guiID)
    {
        super(Material.iron, false);

        _IsActive = isActive;
        _GuiID = guiID;

        this.initializeContainer(GetGaltName() + (_IsActive ? "On" : "Off"));
        this.setHardness(3.5F);
    }

    public GaltMachineBlock(boolean isActive)
    {
        this(isActive, GuiHandler.GUI_ID_MACHINEBLOCK);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        String name = this.GetGaltName();
        this.blockIcon = iconRegister.registerIcon(References.RESOURCESPREFIX + name + ".Side");
        _IconFront = iconRegister.registerIcon(References.RESOURCESPREFIX + name + (_IsActive ? ".FrontOn" : ".FrontOff"));
        _IconTop = iconRegister.registerIcon(References.RESOURCESPREFIX + name + ".Top");
        _IconBottom = iconRegister.registerIcon(References.RESOURCESPREFIX + name + ".Bottom");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int facing /*ie metadata*/)
    {
        switch (side)
        {
            case 1:
                return _IconTop;
            case 0:
                return _IconBottom;
            default:
                return side == facing ? _IconFront : this.blockIcon;
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            Block bz1 = world.getBlock(x, y, z - 1);
            Block bzn1 = world.getBlock(x, y, z + 1);
            Block bx1 = world.getBlock(x - 1, y, z);
            Block bxn1 = world.getBlock(x + 1, y, z);

            byte facing = 3;

            // func_149730_j = isOpaque
            if (bz1.func_149730_j() && !bzn1.func_149730_j())
            {
                facing = 3;
            }
            else if (bzn1.func_149730_j() && !bz1.func_149730_j())
            {
                facing = 1;
            }
            else if (bx1.func_149730_j() && !bxn1.func_149730_j())
            {
                facing = 5;
            }
            else if (bxn1.func_149730_j() && !bx1.func_149730_j())
            {
                facing = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, facing, 2);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            FMLNetworkHandler.openGui(player, GulchMod.instance, _GuiID, world, x, y, z);
        }

        return true;
    }

    @Override
    public abstract TileEntity createNewTileEntity(World var1, int var2);

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (_IsActive)
        {
            int direction = world.getBlockMetadata(x, y, z);

            float x1 = (float) x + 0.5F;
            float y1 = (float) y + random.nextFloat();
            float z1 = (float) z + 0.5F;

            float f = 0.52F;
            float f1 = random.nextFloat() * 0.6F - 0.3F;

            switch (direction)
            {
                case 4:
                    world.spawnParticle("smoke", (double) (x1 - f), (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                    world.spawnParticle("flame", (double) (x1 - f), (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                    break;
                case 5:
                    world.spawnParticle("smoke", (double) (x1 + f), (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                    world.spawnParticle("flame", (double) (x1 + f), (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                    break;
                case 2:
                    world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, 0D);
                    world.spawnParticle("flame", (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, 0D);
                    break;
                case 3:
                    world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 0D);
                    world.spawnParticle("flame", (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 0D);
                    break;
            }
        }
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemBlock)
    {
        int playerFacingCordinal = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.F) + 0.5D) & 3;

        switch (playerFacingCordinal)
        {
            case 0:
                world.setBlockMetadataWithNotify(x, y, z, 2, 2);
                break;
            case 1:
                world.setBlockMetadataWithNotify(x, y, z, 5, 2);
                break;
            case 2:
                world.setBlockMetadataWithNotify(x, y, z, 3, 2);
                break;
            case 3:
                world.setBlockMetadataWithNotify(x, y, z, 4, 2);
                break;
        }

        if (itemBlock.hasDisplayName())
        {
            ((GaltTileEntity) world.getTileEntity(x, y, z)).setGuiDisplayName(itemBlock.getDisplayName());
        }


    }

    public static void updateMachineBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord)
    {
        updateMachineBlockState(active, worldObj, xCoord, yCoord, zCoord, null, null);
    }

    public static void updateMachineBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord, Block activeBlock, Block inactiveBlock)
    {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
        _KeepInventory = true;

        if (activeBlock != null && inactiveBlock != null)
        {
            worldObj.setBlock(xCoord, yCoord, zCoord, active ? activeBlock : inactiveBlock);
        }
//        else
//        {
////            worldObj.setBlock(xCoord, yCoord, zCoord, this);
//        }

        _KeepInventory = false;

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata)
    {
        if (!_KeepInventory)
        {
            GaltTileEntityMachine tileentity = (GaltTileEntityMachine) world.getTileEntity(x, y, z);

            if (tileentity != null)
            {
                for (int i1 = 0; i1 < tileentity.getSizeInventory(); ++i1)
                {
                    ItemStack itemstack = tileentity.getStackInSlot(i1);

                    if (itemstack != null)
                    {
                        float f = _Random.nextFloat() * 0.8F + 0.1F;
                        float f1 = _Random.nextFloat() * 0.8F + 0.1F;
                        float f2 = _Random.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0)
                        {
                            int j1 = _Random.nextInt(21) + 10;

                            if (j1 > itemstack.stackSize)
                            {
                                j1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j1;
                            EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double) ((float) _Random.nextGaussian() * f3);
                            entityitem.motionY = (double) ((float) _Random.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double) ((float) _Random.nextGaussian() * f3);
                            world.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                world.func_147453_f(x, y, z, oldBlock);
            }
        }

        super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
    }

    @Override
    public abstract Item getItemDropped(int slot, Random random, int j);

}
