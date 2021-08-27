package com.mapleman.maplemod.world.gen;


import com.mapleman.maplemod.MapleMod;
import com.mapleman.maplemod.block.MapleTree;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = MapleMod.MOD_ID)
public class ModTreeGeneration
{
    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event)
    {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY,
                event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(BiomeDictionary.Type.CONIFEROUS))
        {

                List<Supplier<ConfiguredFeature<?, ?>>> base =
                        event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> Feature.TREE.withConfiguration(MapleTree.MAPLE_TREE_CONFIG)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(0, 0.05f, 1))));
        }
    }
}
