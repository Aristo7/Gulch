package com.johngalt.gulch.proxy;

import com.johngalt.gulch.entities.EntityBlasterBolt;
import com.johngalt.gulch.renderers.RenderBlasterAmmo;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * Created on 6/13/2014.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityBlasterBolt.class, new RenderBlasterAmmo());

    }
}
