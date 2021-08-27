package com.mapleman.maplemod.screens;

import com.mapleman.maplemod.MapleMod;
import com.mapleman.maplemod.container.AnvilContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;


public class AnvilScreen extends ContainerScreen<AnvilContainer>
{
    private final ResourceLocation GUI = new ResourceLocation(MapleMod.MOD_ID,
            "textures/gui/anvil_gui.png");

    public AnvilScreen(AnvilContainer container, PlayerInventory inv, ITextComponent name)
    {
        super(container, inv, name);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y)
    {
        drawString(matrixStack, Minecraft.getInstance().fontRenderer, "Smash Diamonds", 30, 30, 0xffffff);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }


}
