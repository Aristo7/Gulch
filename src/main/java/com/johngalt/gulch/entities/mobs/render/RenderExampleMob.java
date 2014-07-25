package com.johngalt.gulch.entities.mobs.render;

import com.johngalt.gulch.entities.mobs.ExampleMob;
import com.johngalt.gulch.entities.mobs.models.ModelExampleMob;
import com.johngalt.gulch.lib.References;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

/**
 * Created by on 7/4/2014.
 */
public class RenderExampleMob extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation(
            References.MODID + ":textures/model/ExampleMob.png");

    protected ModelExampleMob model;

    public RenderExampleMob(ModelBase model, float shadowSize)
    {
        super(model, shadowSize);

        this.model = (ModelExampleMob) model;
    }

    public void renderExample(ExampleMob mob, double x, double y, double z, float u, float v)
    {
        super.doRender(mob, x, y, z, u, v);
    }

    public void doRenderLiving(EntityLiving living, double x, double y, double z, float u, float v)
    {
        renderExample((ExampleMob) living, x, y, z, u, v);
    }

    public void doRender(Entity entity, double x, double y, double z, float u, float v)
    {
        renderExample((ExampleMob) entity, x, y, z, u, v);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     *
     * @param entity
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
}
