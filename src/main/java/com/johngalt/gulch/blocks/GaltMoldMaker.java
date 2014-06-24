package com.johngalt.gulch.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created on 6/23/2014.
 */
public class GaltMoldMaker extends GaltCommonContainer
{
    private final Random _Rand = new Random();
    private final boolean _Activated;
    private static boolean field_149934_M;
    @SideOnly(Side.CLIENT)
    private IIcon _IconTopBottom;
    @SideOnly(Side.CLIENT)
    private IIcon _IconFront;

    protected GaltMoldMaker(boolean active)
    {
        super(Material.rock);
        this._Activated = active;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World world, int posx, int posy, int posz)
    {
        super.onBlockAdded(world, posx, posy, posz);
        this.setDirectionWithMetadata(world, posx, posy, posz);
    }

    private void setDirectionWithMetadata(World world, int posx, int posy, int posz)
    {
        if (!world.isRemote)
        {
            Block block = world.getBlock(posx, posy, posz - 1);
            Block block1 = world.getBlock(posx, posy, posz + 1);
            Block block2 = world.getBlock(posx - 1, posy, posz);
            Block block3 = world.getBlock(posx + 1, posy, posz);
            byte b0 = 3;

            // func_149730_j is isOpaque()
            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(posx, posy, posz, b0, 2);
        }
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int frontMetadata)
    {
        if (side == 1)
        {
            return this._IconTopBottom;
        }
        else if (side == 0)
        {
            return this._IconTopBottom;
        }
        else if (side != frontMetadata)
        {
            return this.blockIcon;
        }
        else
        {
            return this._IconFront;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iron)
    {
        this.blockIcon = iron.registerIcon("furnace_side");
        this._IconFront = iron.registerIcon(this._Activated ? "furnace_front_on" : "furnace_front_off");
        this._IconTopBottom = iron.registerIcon("furnace_top");
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int posx, int posy, int posz, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityFurnace tileentityfurnace = (TileEntityFurnace) world.getTileEntity(posx, posy, posz);

            if (tileentityfurnace != null)
            {
                player.func_146101_a(tileentityfurnace);
            }

            return true;
        }
    }

    /**
     * Update which block the furnace is using depending on whether or not it is burning
     */
    public static void updateFurnaceBlockState(boolean activated, World world, int posx, int posy, int posz)
    {
        int meta = world.getBlockMetadata(posx, posy, posz);
        TileEntity tileentity = world.getTileEntity(posx, posy, posz);
        field_149934_M = true;

        if (activated)
        {
            world.setBlock(posx, posy, posz, Blocks.lit_furnace);
        }
        else
        {
            world.setBlock(posx, posy, posz, Blocks.furnace);
        }

        field_149934_M = false;
        world.setBlockMetadataWithNotify(posx, posy, posz, meta, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(posx, posy, posz, tileentity);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_)
    {
        return new TileEntityFurnace();
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int posx, int posy, int posz, EntityLivingBase player, ItemStack itemStackFurnace)
    {
        int direction = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (direction == 0)
        {
            world.setBlockMetadataWithNotify(posx, posy, posz, 2, 2);
        }

        if (direction == 1)
        {
            world.setBlockMetadataWithNotify(posx, posy, posz, 5, 2);
        }

        if (direction == 2)
        {
            world.setBlockMetadataWithNotify(posx, posy, posz, 3, 2);
        }

        if (direction == 3)
        {
            world.setBlockMetadataWithNotify(posx, posy, posz, 4, 2);
        }

        if (itemStackFurnace.hasDisplayName())
        {
            ((TileEntityFurnace) world.getTileEntity(posx, posy, posz)).func_145951_a(itemStackFurnace.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World world, int posx, int posy, int posz, Block brokenBlock, int metadata)
    {
        if (!field_149934_M)
        {
            TileEntityFurnace tileentityfurnace = (TileEntityFurnace) world.getTileEntity(posx, posy, posz);

            if (tileentityfurnace != null)
            {
                for (int i1 = 0; i1 < tileentityfurnace.getSizeInventory(); ++i1)
                {
                    ItemStack itemstack = tileentityfurnace.getStackInSlot(i1);

                    if (itemstack != null)
                    {
                        float f = _Rand.nextFloat() * 0.8F + 0.1F;
                        float f1 = _Rand.nextFloat() * 0.8F + 0.1F;
                        float f2 = _Rand.nextFloat() * 0.8F + 0.1F;

                        // expunge items at 10 to 21 items at a time
                        while (itemstack.stackSize > 0)
                        {
                            int j1 = _Rand.nextInt(21) + 10;

                            if (j1 > itemstack.stackSize)
                            {
                                j1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j1;
                            EntityItem entityitem = new EntityItem(world, (double) ((float) posx + f), (double) ((float) posy + f1), (double) ((float) posz + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double) ((float) this._Rand.nextGaussian() * f3);
                            entityitem.motionY = (double) ((float) this._Rand.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double) ((float) this._Rand.nextGaussian() * f3);
                            world.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                // fire events for neighboring blocks of this change
                world.func_147453_f(posx, posy, posz, brokenBlock);
            }
        }

        super.breakBlock(world, posx, posy, posz, brokenBlock, metadata);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int posx, int posy, int posz, Random rand)
    {
        if (this._Activated)
        {
            int metadata = world.getBlockMetadata(posx, posy, posz);
            float x = (float) posx + 0.5F;
            float y = (float) posy + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
            float z = (float) posz + 0.5F;
            float xoffset = 0.52F;
            float zoffset = rand.nextFloat() * 0.6F - 0.3F;

            if (metadata == 4)
            {
                world.spawnParticle("smoke", (double) (x - xoffset), (double) y, (double) (z + zoffset), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (x - xoffset), (double) y, (double) (z + zoffset), 0.0D, 0.0D, 0.0D);
            }
            else if (metadata == 5)
            {
                world.spawnParticle("smoke", (double) (x + xoffset), (double) y, (double) (z + zoffset), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (x + xoffset), (double) y, (double) (z + zoffset), 0.0D, 0.0D, 0.0D);
            }
            else if (metadata == 2)
            {
                world.spawnParticle("smoke", (double) (x + zoffset), (double) y, (double) (z - xoffset), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (x + zoffset), (double) y, (double) (z - xoffset), 0.0D, 0.0D, 0.0D);
            }
            else if (metadata == 3)
            {
                world.spawnParticle("smoke", (double) (x + zoffset), (double) y, (double) (z + xoffset), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (x + zoffset), (double) y, (double) (z + xoffset), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    @Override
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    @Override
    public int getComparatorInputOverride(World world, int posx, int posy, int posz, int metadata)
    {
        return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(posx, posy, posz));
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World world, int posx, int posy, int posz)
    {
        return Item.getItemFromBlock(Blocks.furnace);
    }
}
