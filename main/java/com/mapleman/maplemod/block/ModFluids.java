package com.mapleman.maplemod.block;

import com.mapleman.maplemod.MapleMod;
import com.mapleman.maplemod.item.ModItems;
import com.mapleman.maplemod.util.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;

public class ModFluids
{
    public static final ResourceLocation MAPLE_SAP_STILL_RL = new ResourceLocation(MapleMod.MOD_ID,
            "blocks/maple_sap_still");

    public static final ResourceLocation MAPLE_SAP_FLOWING_RL = new ResourceLocation(MapleMod.MOD_ID,
            "blocks/maple_sap_flowing");

    public static final ResourceLocation MAPLE_SAP_OVERLAY_RL = new ResourceLocation(MapleMod.MOD_ID,
            "blocks/maple_sap_overlay");

    public static final RegistryObject<FlowingFluid> MAPLE_SAP
            = Registration.FLUIDS.register("maple_sap",
            () -> new ForgeFlowingFluid.Source(ModFluids.MAPLE_SAP_PROPERTIES));

    public static final RegistryObject<FlowingFluid> MAPLE_SAP_FLOWING
            = Registration.FLUIDS.register("maple_sap_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.MAPLE_SAP_PROPERTIES));

    public static final ForgeFlowingFluid.Properties MAPLE_SAP_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MAPLE_SAP.get(), () -> MAPLE_SAP_FLOWING.get(), FluidAttributes.builder(MAPLE_SAP_STILL_RL, MAPLE_SAP_FLOWING_RL)
            .density(15).viscosity(5).sound(SoundEvents.ITEM_BUCKET_FILL).overlay(MAPLE_SAP_OVERLAY_RL)).slopeFindDistance(3).levelDecreasePerBlock(3)
            .block(() -> ModFluids.MAPLE_SAP_BLOCK.get()).bucket(() -> ModItems.MAPLE_SAP_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> MAPLE_SAP_BLOCK = Registration.BLOCKS.register("maple_sap",
            () -> new FlowingFluidBlock(() -> ModFluids.MAPLE_SAP.get(), AbstractBlock.Properties.create(Material.WATER)
                    .doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));

    public static void register() {}
}
