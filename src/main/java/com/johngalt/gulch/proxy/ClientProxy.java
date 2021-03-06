package com.johngalt.gulch.proxy;


import com.johngalt.gulch.blocks.GaltRenderedBlockInterface;
import com.johngalt.gulch.blocks.common.GaltCommonBlockContainer;
import com.johngalt.gulch.entities.EntityBlasterBolt;
import com.johngalt.gulch.renderers.GaltBlockRenderer;
import com.johngalt.gulch.renderers.GaltInventoryBlockRenderHelper;
import com.johngalt.gulch.renderers.RenderBlasterAmmo;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.crash.CrashReport;
import net.minecraft.item.Item;
import net.minecraft.util.ReportedException;
import net.minecraftforge.client.MinecraftForgeClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/13/2014.
 */
public class ClientProxy extends CommonProxy
{
    private static List<GaltCommonBlockContainer> _RenderedBlocks = new ArrayList<GaltCommonBlockContainer>();

    @Override
    public void RegisterRenderers()
    {
        super.RegisterRenderers();

        RenderingRegistry.registerEntityRenderingHandler(EntityBlasterBolt.class, new RenderBlasterAmmo());

        try
        {
            // Render custom rendered blocks
            for (GaltCommonBlockContainer block : ClientProxy._RenderedBlocks)
            {
                GaltBlockRenderer renderer;

                if (block instanceof GaltRenderedBlockInterface)
                {
                    renderer = new GaltBlockRenderer(((GaltRenderedBlockInterface) block).GetRenderHelper().GetModel(), ((GaltRenderedBlockInterface) block).GetRenderHelper().GetTextureLocation());
                    ClientRegistry.bindTileEntitySpecialRenderer(((GaltRenderedBlockInterface) block).GetTileEntityCustRenderClass(), renderer);
                }
                else
                {
                    throw new Exception("Unreconized Rendering class!");
                }

                MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), new GaltInventoryBlockRenderHelper(renderer, block.createNewTileEntity(null, 0)));

            }
        }
        catch (Throwable throwable2)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable2, "Registering renderers");
            throw new ReportedException(crashreport);
        }
    }

    @Override
    public void RegisterRenderedBlock(GaltCommonBlockContainer galtRenderedBlock)
    {
        if (galtRenderedBlock instanceof GaltRenderedBlockInterface)
            _RenderedBlocks.add(galtRenderedBlock);
    }
}
