package com.mapleman.maplemod.block;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class MapleTree extends Tree
{
    private static final int BASE_HEIGHT = 4;
    private static final int FIRST_RAND_HEIGHT = 3;
    private static final int SECOND_RAND_HEIGHT = 5;

    private static final int LEAVE_RADIUS = 3;
    private static final int LEAVE_OFFSET = 0;
    private static final int LEAVE_HEIGHT = 3;


    public static BaseTreeFeatureConfig MAPLE_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.MAPLE_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.MAPLE_LEAVES.get().getDefaultState()),
            new BlobFoliagePlacer(FeatureSpread.func_242252_a(LEAVE_RADIUS),
                    FeatureSpread.func_242252_a(LEAVE_OFFSET), LEAVE_HEIGHT),
            new StraightTrunkPlacer(BASE_HEIGHT, FIRST_RAND_HEIGHT, SECOND_RAND_HEIGHT),
            new TwoLayerFeature(1, 0, 1)
    )).build();

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive)
    {
        return Feature.TREE.withConfiguration(MAPLE_TREE_CONFIG);
    }
}
