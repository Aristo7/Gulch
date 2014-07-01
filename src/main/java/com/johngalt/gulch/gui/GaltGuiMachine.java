package com.johngalt.gulch.gui;

import com.johngalt.gulch.lib.References;
import com.johngalt.gulch.tileentities.GaltTileEntityMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created on 6/27/2014.
 */
public class GaltGuiMachine extends GuiContainer
{
    public ResourceLocation Background;

    private GaltTileEntityMachine _MachineTileEntity;


    public GaltGuiMachine(InventoryPlayer inventory, GaltTileEntityMachine tileentity, String filenameWithExt)
    {
        super(new GaltContainerMachine(inventory, tileentity));

        _MachineTileEntity = tileentity;

        Background = new ResourceLocation(References.RESOURCESPREFIX + "textures/gui/" + filenameWithExt);

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void drawGuiContainerForegroundLayer(int part1, int par2)
    {

        String name;
        if (_MachineTileEntity.hasCustomInventoryName())
        {
            name = _MachineTileEntity.getInventoryName();
        }
        else
        {
            name = I18n.format("Machine Block");
        }

        this.fontRendererObj.drawString(name, getCenterXForWord(name), 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("Inventory"), getRightXForWord("Inventory"), this.ySize - 96 + 2, 4210752);
    }

    private int getCenterXForWord(String word)
    {
        return this.xSize / 2 - this.fontRendererObj.getStringWidth(word) / 2;
    }

    private int getRightXForWord(String word)
    {
        return this.xSize - this.fontRendererObj.getStringWidth(word) - 8;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(Background);
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if (_MachineTileEntity.isBurning())
        {
            int k = _MachineTileEntity.GetBurnTimeRemainingScaled(40);
            int j = 40 - k;
            this.drawTexturedModalRect(this.guiLeft + 29, this.guiTop + 65, 176, 0, 40 - j, 10);
        }

        int k = _MachineTileEntity.GetCookProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 34, 176, 10, k + 1, 16);
    }
}
