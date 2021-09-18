package com.mapleman.maplemod.item;

import com.mapleman.maplemod.MapleMod;
import com.mapleman.maplemod.block.ModFluids;
import com.mapleman.maplemod.util.Registration;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import org.lwjgl.system.CallbackI;

public class ModItems
{
    public static final RegistryObject<Item> OBSIDIAN_HAMMER =
            Registration.ITEMS.register("obsidian_hammer",
            () -> new Item(new Item.Properties().group(MapleMod.MAPLEMOD_TAB)));

    public static final RegistryObject<Item> CLEAVER =
            Registration.ITEMS.register("cleaver",
                    () -> new Item(new Item.Properties().group(MapleMod.MAPLEMOD_TAB)));

    public static final RegistryObject<Item> COOKED_BACON =
            Registration.ITEMS.register("cooked_bacon",
                    () -> new CookedBacon());

    public static final RegistryObject<Item> MAPLE_BACON =
            Registration.ITEMS.register("maple_bacon",
                    () -> new MapleBacon());

    public static final RegistryObject<Item> RAW_BACON =
            Registration.ITEMS.register("raw_bacon",
                    () -> new Item(new Item.Properties().group(MapleMod.MAPLEMOD_TAB)));

    public static final RegistryObject<Item> MAPLE_LEAF =
            Registration.ITEMS.register("maple_leaf",
                    () -> new Item(new Item.Properties().group(MapleMod.MAPLEMOD_TAB)));

    public static final RegistryObject<Item> DIAMOND_SHARD =
            Registration.ITEMS.register("diamond_shard",
                    () -> new Item(new Item.Properties().group(MapleMod.MAPLEMOD_TAB)));

    public static final RegistryObject<Item> MAPLE_SAP_BUCKET =
            Registration.ITEMS.register("maple_sap_bucket",
                    () -> new BucketItem(ModFluids.MAPLE_SAP::get,
                            new Item.Properties().group(MapleMod.MAPLEMOD_TAB).maxStackSize(1)));

    public static final RegistryObject<Item> MAPLE_SYRUP_BUCKET =
            Registration.ITEMS.register("maple_syrup_bucket",
                    () -> new BucketItem(ModFluids.MAPLE_SYRUP::get,
                            new Item.Properties().group(MapleMod.MAPLEMOD_TAB).maxStackSize(1)));

    public static final RegistryObject<Item> MAPLE_DIPPED_SWORD =
            Registration.ITEMS.register("maple_dipped_sword",
                    () -> new SwordItem(ItemTier.GOLD, 8, 3, new Item.Properties()
                            .defaultMaxDamage(1561)
                            .group(MapleMod.MAPLEMOD_TAB)));

    public static final RegistryObject<Item> MAPLE_DIPPED_AXE =
            Registration.ITEMS.register("maple_dipped_axe",
                    () -> new AxeItem(ItemTier.GOLD,10, 2, new Item.Properties()
                            .defaultMaxDamage(1561)
                            .addToolType(ToolType.AXE, 3)
                            .group(MapleMod.MAPLEMOD_TAB)));

    public static final RegistryObject<Item> MAPLE_DIPPED_PICKAXE =
            Registration.ITEMS.register("maple_dipped_pickaxe",
                    () -> new PickaxeItem(ItemTier.GOLD,6, 3, new Item.Properties()
                            .defaultMaxDamage(1561)
                            .addToolType(ToolType.PICKAXE, 3)
                            .group(MapleMod.MAPLEMOD_TAB)));

    public static final RegistryObject<Item> MAPLE_DIPPED_SHOVEL =
            Registration.ITEMS.register("maple_dipped_shovel",
                    () -> new ShovelItem(ItemTier.GOLD,3, 3, new Item.Properties()
                            .defaultMaxDamage(1561)
                            .addToolType(ToolType.SHOVEL, 3)
                            .group(MapleMod.MAPLEMOD_TAB)));

    public static void register() {}


}
