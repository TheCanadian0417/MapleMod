package com.mapleman.maplemod.tileentity;

import com.mapleman.maplemod.block.ModBlocks;
import com.mapleman.maplemod.util.Registration;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities
{
    public static final RegistryObject<TileEntityType<AnvilTile>> ANVIL_TILE_ENTITY
            = Registration.TILE_ENTITY_TYPES.register("anvil_tile_entity", () -> TileEntityType.Builder.create(
            () -> new AnvilTile(), ModBlocks.OBSIDIAN_ANVIL.get()).build(null));

    public static final RegistryObject<TileEntityType<TapTile>> TAP_TILE_ENTITY
            = Registration.TILE_ENTITY_TYPES.register("tap_tile_entity", () -> TileEntityType.Builder.create(
            () -> new TapTile(), ModBlocks.DIAMOND_TAP.get()).build(null));
    public static void register() {}

}
