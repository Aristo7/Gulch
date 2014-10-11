package com.johngalt.gulch.blocks;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.blocks.common.GaltMachineBlock;
import com.johngalt.gulch.blocks.common.GaltRenderedBlockHelper;
import com.johngalt.gulch.items.GaltItems;
import com.johngalt.gulch.model.GaltMoldStationModel;
import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import com.johngalt.gulch.tileentities.GaltMoldStationTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.Random;

/**
 * Created on 6/28/2014.
 */
public class GaltMoldStationBlock extends GaltMachineBlock implements GaltRenderedBlockInterface, IGaltRecipes
{
    private GaltRenderedBlockHelper _RenderHelper;
    private static Block _DroppedBlockItem;

    public GaltMoldStationBlock()
    {
        super(false, Material.wood, false);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setBlockBounds(0, 0, 0, 1, 0.75F, 1);
        this.CustomRendered = true;

        _DroppedBlockItem = this;

        _RenderHelper = new GaltRenderedBlockHelper(new GaltMoldStationModel(), "textures/blocks/GaltModelMoldingStation.png", this);
        GulchMod.proxy.registerTileEntity(GaltMoldStationTileEntity.class, GaltMoldStationTileEntity.class.getSimpleName());

    }


    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new GaltMoldStationTileEntity();
    }

    @Override
    public Class<? extends TileEntity> GetTileEntityCustRenderClass()
    {
        return GaltMoldStationTileEntity.class;
    }

    @Override
    public int getRenderType()
    {
        return GaltRenderedBlockHelper.getRenderType();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return GaltRenderedBlockHelper.isOpaqueCube();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return GaltRenderedBlockHelper.renderAsNormalBlock();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemBlock)
    {
        GaltRenderedBlockHelper.onBlockPlacedBy(world, x, y, z, player, itemBlock);

        TileEntity entity = world.getTileEntity(x, y, z);
        if (entity instanceof GaltMoldStationTileEntity)
        {
            int dir = (MathHelper.floor_double((double) ((player.rotationYaw * 4F) / 360F) + 0.5D) & 3) % 4;
            ((GaltMoldStationTileEntity) entity).SetDirection((short) dir);
        }
    }

    @Override
    public Item getItemDropped(int slot, Random random, int j)
    {
        return Item.getItemFromBlock(_DroppedBlockItem == null ? this : _DroppedBlockItem);
    }

    @Override
    public GaltRenderedBlockHelper GetRenderHelper()
    {
        return _RenderHelper;
    }


    @Override
    public void RegisterRecipes()
    {
        GaltRecipes.RegisterRecipe(new ShapedOreRecipe(new ItemStack(this, 1), " k ", "ppp", "l l", 'k', GaltItems.Knife.GetOreDictName(), 'p', "plankWood", 'l', "treeWood"));
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        TileEntity entity = world.getTileEntity(x, y, z);
        if (entity instanceof GaltMoldStationTileEntity)
        {
            if (((GaltMoldStationTileEntity) entity).IsMBComplete())
                return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
        }

        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {

    }
}
