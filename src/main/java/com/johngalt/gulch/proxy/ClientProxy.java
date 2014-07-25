package com.johngalt.gulch.proxy;


import com.johngalt.gulch.blocks.common.GaltRenderedBlock;
import com.johngalt.gulch.entities.EntityBlasterBolt;
import com.johngalt.gulch.renderers.GaltBlockRenderer;
import com.johngalt.gulch.renderers.GaltInventoryBlockRenderHelper;
import com.johngalt.gulch.renderers.RenderBlasterAmmo;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/13/2014.
 */
public class ClientProxy extends CommonProxy
{
    private static List<GaltRenderedBlock> _RenderedBlocks = new ArrayList<GaltRenderedBlock>();

    @Override
    public void RegisterRenderers()
    {
        super.RegisterRenderers();

        RenderingRegistry.registerEntityRenderingHandler(EntityBlasterBolt.class, new RenderBlasterAmmo());

        // Render custom rendered blocks
        for (GaltRenderedBlock block : ClientProxy._RenderedBlocks)
        {
            GaltBlockRenderer renderer = new GaltBlockRenderer(block.GetModel(), block.GetTextureLocation());
            ClientRegistry.bindTileEntitySpecialRenderer(block.GetTileEntityCustRenderClass(), renderer);
            MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), new GaltInventoryBlockRenderHelper(renderer, block.createNewTileEntity(null, 0)));
        }
    }


    public static void RegisterRenderedBlock(GaltRenderedBlock galtRenderedBlock)
    {
        _RenderedBlocks.add(galtRenderedBlock);
    }
}
