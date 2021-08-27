package com.mapleman.maplemod.container;

import com.mapleman.maplemod.util.Registration;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers
{
    public static final RegistryObject<ContainerType<AnvilContainer>> ANVIL_CONTAINER
            = Registration.CONTAINERS.register("anvil_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new AnvilContainer(windowId, world, pos, inv, inv.player);
            })));

    public static void register() { }
}
