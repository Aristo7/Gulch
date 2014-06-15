package com.johngalt.gulch.gui;

import com.johngalt.gulch.References;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/**
 * Created on 6/14/2014.
 */
public class GaltGUI extends GuiScreen
{
    /**
     * The gui file needs to be 256x256.
     * The GUI ITSELF can have any size you want
     * Define them here
     * These are the GUI sizes!
     */
    int xSize = 176;
    int ySize = 214;
    private static final ResourceLocation backgroundimage = new ResourceLocation(References.MODID.toLowerCase() + ":" +
            "textures/gui/galtguiwithprogress.png");

    public GaltGUI()
    {

    }

    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        //Bind Texture
        this.mc.getTextureManager().bindTexture(backgroundimage);
        // set the x for the texture, Total width - textureSize / 2
        par2 = (this.width - xSize) / 2;
        // set the y for the texture, Total height - textureSize - 30 (up) / 2,
        int j = (this.height - ySize - 30) / 2;
        // draw the texture
        drawTexturedModalRect(par2, j, 0, 0, xSize, ySize);
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
