package com.johngalt.gulch.blocks;

/**
 * Created by on 6/16/2014.
 */

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.References;
import com.johngalt.gulch.dimension.GulchTeleporter;
import com.johngalt.gulch.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class GulchPortalBlock extends BlockPortal
{
    public GulchPortalBlock()
    {
        super();
        setBlockName(Strings.GulchPortalBlockName);
        this.setCreativeTab(GulchMod.getCreativeTab());

        GameRegistry.registerBlock(this, this.getUnwrappedUnlocalizedName(this.getUnlocalizedName()));
    }

    public String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", References.RESOURCESPREFIX,
                getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        if ((par5Entity.ridingEntity == null) && (par5Entity.riddenByEntity == null) && ((par5Entity instanceof EntityPlayerMP)))
        {
            EntityPlayerMP player = (EntityPlayerMP) par5Entity;

            MinecraftServer mServer = MinecraftServer.getServer();

            if (player.timeUntilPortal > 0)
            {
                player.timeUntilPortal = 10;
            }
            else if (player.dimension != GulchMod.gulchDimension)
            {
                player.timeUntilPortal = 10;

                player.mcServer.getConfigurationManager().transferPlayerToDimension(player, GulchMod.gulchDimension,
                        new GulchTeleporter(mServer.worldServerForDimension(GulchMod.gulchDimension)));
            }
            else
            {
                player.timeUntilPortal = 10;
                player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0,
                        new GulchTeleporter(mServer.worldServerForDimension(1)));
            }
        }
    }

    @Override
    public boolean func_150000_e(World par1World, int par2, int par3, int par4)
    {
        byte b0 = 0;
        byte b1 = 0;

        if (par1World.getBlock(par2 - 1, par3, par4) == GaltBlocks.portalFrame || par1World.getBlock(par2 + 1, par3,
                par4) == GaltBlocks.portalFrame)
        {
            b0 = 1;
        }

        if (par1World.getBlock(par2, par3, par4 - 1) == GaltBlocks.portalFrame || par1World.getBlock(par2, par3,
                par4 + 1) == GaltBlocks.portalFrame)
        {
            b1 = 1;
        }

        if (b0 == b1)
        {
            return false;
        }
        else
        {
            if (par1World.isAirBlock(par2 - b0, par3, par4 - b1))
            {
                par2 -= b0;
                par4 -= b1;
            }

            int l;
            int i1;

            for (l = -1; l <= 2; ++l)
            {
                for (i1 = -1; i1 <= 3; ++i1)
                {
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
                    {
                        Block j1 = par1World.getBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);
                        boolean isAirBlock = par1World.isAirBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);

                        if (flag)
                        {
                            if (j1 != GaltBlocks.portalFrame)
                            {
                                return false;
                            }
                        }
                        else if (!isAirBlock && j1 != Blocks.fire)
                        {
                            return false;
                        }
                    }
                }
            }

            for (l = 0; l < 2; ++l)
            {
                for (i1 = 0; i1 < 3; ++i1)
                {
                    par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, GaltBlocks.portalBlock, 0, 2);
                }
            }

            return true;
        }
    }

    /*
        The logic is simply to replace portal blocks with air if any of the blocks nearby have changed.
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        // This needs to be fixed. I can't get it to work without crashing MC

        /*
        int bottomMostPortalY;

        for (bottomMostPortalY = y; world.getBlock(x, bottomMostPortalY - 1, z) == this; --bottomMostPortalY)
        {
        }

        if (world.getBlock(x, bottomMostPortalY - 1, z) != GaltBlocks.portalFrame)
        {
            // So the bottom most block after portal block was not a frame!
            // Thus erase this portal block.
            // The other portal blocks will get triggered through the disappearance of this one.
            world.setBlockToAir(x, y, z);
        }
        else
        {
            byte orientationByteX = 0;
            byte orientationByteZ = 1;

            if (world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this)
            {
                orientationByteX = 1;
                orientationByteZ = 0;
            }

            int topMostPortalY;
            int highestPossiblePortal = 3;

            for (topMostPortalY = 1; topMostPortalY <= highestPossiblePortal &&
                    world.getBlock(x, bottomMostPortalY + topMostPortalY, z) == this; ++topMostPortalY)
            {
            }

            if (topMostPortalY == 3 && world.getBlock(x, bottomMostPortalY + topMostPortalY, z) ==
                    GaltBlocks.portalFrame)
            {
                boolean anotherPortalOnX = world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this;
                boolean anotherPortalOnZ = world.getBlock(x, y, z - 1) == this || world.getBlock(x, y, z + 1) == this;

                if (anotherPortalOnX && anotherPortalOnZ)
                {
                    // Portal on both sides means an invalid portal so start removing
                    world.setBlockToAir(x, y, z);
                }
                else
                {
                    // This pathetic logic means: we expect to either have
                    // 1) frame one one side and portal on the other OR
                    // 2) portal on one side and frame on the other
                    if ((world.getBlock(x + orientationByteX, y, z + orientationByteZ) != GaltBlocks.portalFrame ||
                            world.getBlock(x - orientationByteX, y, z - orientationByteZ) != this) &&
                            (world.getBlock(x - orientationByteX, y, z - orientationByteZ) != GaltBlocks.portalFrame ||
                                    world.getBlock(x + orientationByteX, y, z + orientationByteZ) != this))
                    {
                        // not 1) or 2) so removing portal
                        world.setBlockToAir(x, y, z);
                    }
                }
            }
            else
            {
                // Portal too high, start removing
                world.setBlockToAir(x, y, z);
            }
        }
        */
    }
}