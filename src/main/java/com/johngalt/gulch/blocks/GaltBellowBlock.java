package com.johngalt.gulch.blocks;

import com.johngalt.gulch.blocks.common.GaltCommonBlockContainer;
import com.johngalt.gulch.blocks.common.GaltRenderedBlockHelper;
import com.johngalt.gulch.items.GaltItems;
import com.johngalt.gulch.model.GaltModelBellow;
import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import com.johngalt.gulch.tileentities.GaltBellowTECustRender;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created on 7/3/2014.
 */
public class GaltBellowBlock extends GaltCommonBlockContainer implements GaltRenderedBlockInterface, IGaltRecipes
{
    public GaltRenderedBlockHelper _RenderHelper;

    public GaltBellowBlock()
    {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setBlockBounds(0, 0, .0625F, 1, 0.75F, 0.9375F);

        _RenderHelper = new GaltRenderedBlockHelper(new GaltModelBellow(), "textures/blocks/GaltModelBellow.png", this);
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
    }

    @Override
    public GaltRenderedBlockHelper GetRenderHelper()
    {
        return _RenderHelper;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new GaltBellowTECustRender();
    }

    @Override
    public Class<? extends TileEntity> GetTileEntityCustRenderClass()
    {
        return GaltBellowTECustRender.class;
    }

    @Override
    public void RegisterRecipes()
    {
        GaltRecipes.RegisterRecipe(new ShapedOreRecipe(new ItemStack(this, 1), "ppk", "lls", "pp ", 'k', GaltItems.Knife.GetOreDictName(), 'p', "plankWood", 'l', Items.leather, 's', "stickWood" ));
    }
}
