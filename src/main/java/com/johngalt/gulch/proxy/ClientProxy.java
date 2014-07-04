package com.johngalt.gulch.proxy;

import com.johngalt.gulch.blocks.common.GaltBlocks;
import com.johngalt.gulch.entities.EntityBlasterBolt;
import com.johngalt.gulch.renderers.GaltBellowItemRenderer;
import com.johngalt.gulch.renderers.GaltBellowRenderer;
import com.johngalt.gulch.renderers.RenderBlasterAmmo;
import com.johngalt.gulch.tileentities.GaltTileEntityBellow;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created on 6/13/2014.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void RegisterRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityBlasterBolt.class, new RenderBlasterAmmo());


        TileEntitySpecialRenderer renderer = new GaltBellowRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(GaltTileEntityBellow.class, renderer);

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GaltBlocks.BellowBlock), new GaltBellowItemRenderer(renderer, new GaltTileEntityBellow()));
    }

    public void RegisterTileEntitySpecialRenderer()
    {

    }
}
