package com.johngalt.gulch.renderers;

import com.johngalt.gulch.lib.References;
import com.johngalt.gulch.tileentities.common.GaltTECustRenderInterface;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created on 7/1/2014.
 */
public class GaltBlockRenderer extends TileEntitySpecialRenderer
{
    private ResourceLocation _Texture;// = new ResourceLocation(References.RESOURCESPREFIX + "textures/blocks/GaltModelBellow.png");
    private ModelBase _Model;

    public GaltBlockRenderer(ModelBase model, String gulchTextureLocation)
    {
        _Model = model;
        _Texture = new ResourceLocation(References.RESOURCESPREFIX + gulchTextureLocation); //"textures/blocks/GaltModelBellow.png");
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
    {
        short direction = 0;
        if (tileEntity instanceof GaltTECustRenderInterface)
        {
            direction = ((GaltTECustRenderInterface) tileEntity).GetTECustRenderHelper().GetDirection();
        }

        GL11.glPushMatrix();

        {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

            int rotation = 0;
            if (direction == 3)
            {
                rotation = 90;
            }
            if (direction == 2)
            {
                rotation = 180;
            }
            if (direction == 1)
            {
                rotation = 270;
            }
            GL11.glRotatef(rotation, 0.0F, 1.0F, 0F);

            GL11.glRotatef(180, 0, 0, 1);

            this.bindTexture(_Texture);

            GL11.glPushMatrix();
            _Model.render(null, 0, 0, 0, 0, 0, 0.0625F);
            GL11.glPopMatrix();
        }

        GL11.glPopMatrix();
    }


}
