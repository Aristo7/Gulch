package com.johngalt.gulch.gui;

import com.johngalt.gulch.lib.References;
import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created on 6/14/2014.
 */
public class GuiInventory extends GuiContainer
{
    private static final ResourceLocation backgroundimage = new ResourceLocation(References.MODID.toLowerCase() + ":" + "textures/gui/galtguiwithprogress.png");

    private GaltTileEntityContainer tileEntityTestContainer;

    public GuiInventory(InventoryPlayer inventoryPlayer, GaltTileEntityContainer tileEntityTestContainer)
    {
        super(new GaltInventoryContainer(inventoryPlayer, tileEntityTestContainer));
        xSize = 176;
        ySize = 214;

        this.tileEntityTestContainer = tileEntityTestContainer;
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float par3)
    {
        drawGuiContainerBackgroundLayer(par3, mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, par3);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        /*
        Texture details. My arrow (filled) starts at 177, 0 and is 23,23 big
         */
        int arrowU = 177;
        int arrowUDelta = 23;
        int arrowV = 0;
        int arrowVDelta = 23;

        /*
        Draw GUI Background
         */

        //Bind Texture
        this.mc.getTextureManager().bindTexture(backgroundimage);
        // set the x for the texture, Total width - textureSize / 2
        par2 = (this.width - xSize) / 2;
        // set the y for the texture, Total height - textureSize - 30 (up) / 2,
        int j = (this.height - ySize) / 2;
        // draw the texture
        drawTexturedModalRect(par2, j, 0, 0, xSize, ySize);

        /*
        Draw Black arrow
         */
        par2 = (this.width - arrowUDelta) / 2 + 1; //x
        j = (this.height - arrowVDelta) / 2 - 34; //y

            /*
            DrawTexturedModalRect takes 6 params.
            xCoord to display
            yCoord to display
            U, where the part starts it has to draw in your texture (x)
            V, where the part starts it has to draw in your texture (y)
            dU, size of texture (x)
            dV, size of texture (y)
             */
        drawTexturedModalRect(par2, j, arrowU, arrowV, arrowUDelta, arrowVDelta);

        /*
         Draw image to (partially) cover the black arrow)
          */


        par2 = (this.width - arrowUDelta) / 2 + 1;
        j = (this.height - arrowVDelta) / 2 - 34;
        // This time, my V is lower, and my dV depends on how far the smashing progress is.
        drawTexturedModalRect(par2, j, arrowU, (arrowV + arrowVDelta + 1), arrowUDelta, Math.round(arrowVDelta * (((float) this.tileEntityTestContainer.getSmashTime() - this.tileEntityTestContainer.getSmashTimeRemaining()) / this.tileEntityTestContainer.getSmashTime())));

    }
}
