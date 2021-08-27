package com.mapleman.maplemod.item;

import com.mapleman.maplemod.MapleMod;
import com.mapleman.maplemod.block.ModFluids;
import com.mapleman.maplemod.util.Registration;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItems
{
    public static final RegistryObject<Item> OBSIDIAN_HAMMER =
            Registration.ITEMS.register("obsidian_hammer",
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

    public static void register() {}

}
