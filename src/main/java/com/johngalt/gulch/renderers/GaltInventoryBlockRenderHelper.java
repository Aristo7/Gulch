package com.johngalt.gulch.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created on 7/1/2014.
 */
public class GaltInventoryBlockRenderHelper implements IItemRenderer
{
    private TileEntitySpecialRenderer _Renderer;
    private TileEntity _Entity;

    public GaltInventoryBlockRenderHelper(TileEntitySpecialRenderer renderer, TileEntity entity)
    {
        _Entity = entity;
        _Renderer = renderer;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if (type == ItemRenderType.ENTITY)
        {
            GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
        }

        _Renderer.renderTileEntityAt(_Entity, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}
