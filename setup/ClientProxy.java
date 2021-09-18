package com.mapleman.maplemod.setup;

import com.mapleman.maplemod.MapleMod;
import com.mapleman.maplemod.block.ModBlocks;
import com.mapleman.maplemod.container.ModContainers;
import com.mapleman.maplemod.screens.AnvilScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = MapleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy
{

    @Override
    public void init()
    {
        ScreenManager.registerFactory(ModContainers.ANVIL_CONTAINER.get(), AnvilScreen::new);
        RenderTypeLookup.setRenderLayer(ModBlocks.MAPLE_SAPLING.get(), RenderType.getCutout());
    }

    @Override
    public World getClientWorld()
    {
        return Minecraft.getInstance().world;
    }
}
