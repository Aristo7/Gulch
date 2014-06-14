package com.johngalt.gulch.gui;

import com.johngalt.gulch.References;
import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created on 6/14/2014.
 */
public class GuiInventory extends GuiContainer
{
    private static final ResourceLocation backgroundimage = new ResourceLocation(References.MODID.toLowerCase() + ":" + "textures/gui/galtgui.png");

    public GuiInventory(InventoryPlayer inventoryPlayer, GaltTileEntityContainer tileEntityTestContainer)
    {
        super(new GaltInventoryContainer(inventoryPlayer, tileEntityTestContainer));
        xSize = 176;
        ySize = 214;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        //Bind Texture
        this.mc.getTextureManager().bindTexture(backgroundimage);
        // set the x for the texture, Total width - textureSize / 2
        par2 = (this.width - xSize) / 2;
        // set the y for the texture, Total height - textureSize - 30 (up) / 2,
        int j = (this.height - ySize) / 2;
        // draw the texture
        drawTexturedModalRect(par2, j, 0, 0, xSize,  ySize);
    }
}
