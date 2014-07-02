package com.johngalt.gulch.renderers;

import com.johngalt.gulch.lib.References;
import com.johngalt.gulch.model.GaltModelBellow;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created on 7/1/2014.
 */
public class GaltBellowRenderer extends TileEntitySpecialRenderer
{
    private static ResourceLocation _Texture = new ResourceLocation(References.RESOURCESPREFIX + "textures/blocks/GaltModelBellow.png");
    private GaltModelBellow _BellowModel;

    public GaltBellowRenderer()
    {
        _BellowModel = new GaltModelBellow();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();

        {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glRotatef(90, 0, 0, 0);

            this.bindTexture(_Texture);

            GL11.glPushMatrix();
            _BellowModel.renderModel(0.0625F);
            GL11.glPopMatrix();
        }

        GL11.glPopMatrix();
    }
}
