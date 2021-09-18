package com.mapleman.maplemod.block;

import com.mapleman.maplemod.MapleMod;
import com.mapleman.maplemod.util.Registration;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final RegistryObject<Block> OBSIDIAN_ANVIL = register("obsidian_anvil",
            () -> new ObsidianAnvil(AbstractBlock.Properties.create(Material.ANVIL)
                    .hardnessAndResistance(4f).harvestTool(ToolType.PICKAXE).sound(SoundType.ANVIL)));

    public static final RegistryObject<Block> SYRUP_BARREL = register("syrup_barrel",
            () -> new SyrupBarrel(AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2f).harvestTool(ToolType.AXE).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MAPLE_PLANKS = register("maple_planks",
            () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> MAPLE_STAIRS =
            register("maple_stairs", () -> new StairsBlock(() -> ModBlocks.MAPLE_PLANKS.get().getDefaultState(),
                AbstractBlock.Properties.create(Material.WOOD)));

    public static final RegistryObject<Block> MAPLE_FENCE =
            register("maple_fence", () -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD)));

    public static final RegistryObject<Block> MAPLE_FENCE_GATE =
            register("maple_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD)));

    public static final RegistryObject<Block> MAPLE_PRESSURE_PLATE =
            register("maple_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.create(Material.WOOD)));

    public static final RegistryObject<Block> MAPLE_WOOD_SLAB =
            register("maple_wood_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD)));

    public static final RegistryObject<Block> MAPLE_LOG = register("maple_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> MAPLE_LEAVES = register("maple_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> MAPLE_SAPLING = register("maple_sapling",
            () -> new MapleSapling(
                    () -> new MapleTree(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)
            ));

    public static final RegistryObject<Block> DIAMOND_TAP = register("diamond_tap",
            () -> new SapSpout(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(4f).harvestTool(ToolType.PICKAXE)));


    public static void register() {}

    private static <T extends Block>RegistryObject<T> register(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = Registration.BLOCKS.register(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                new Item.Properties().group(MapleMod.MAPLEMOD_TAB)));
        return toReturn;
    }
}
