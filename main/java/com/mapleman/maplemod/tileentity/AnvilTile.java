package com.mapleman.maplemod.tileentity;

import com.mapleman.maplemod.MapleMod;
import com.mapleman.maplemod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AnvilTile extends TileEntity
{
    private final ItemStackHandler itemHandler = createHandler();

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    private int tick = 0;

    public AnvilTile(TileEntityType<?> tileEntityTypeIn)
    {
        super(tileEntityTypeIn);
    }

    public AnvilTile()
    {
        this(ModTileEntities.ANVIL_TILE_ENTITY.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT tag)
    {
        itemHandler.deserializeNBT(tag.getCompound("inv"));

        super.read(state, tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag)
    {
        tag.put("inv", itemHandler.serializeNBT());

        return super.write(tag);
    }

    private ItemStackHandler createHandler()
    {
        return new ItemStackHandler(3)
        {
            protected void onContentsChanged(int slot)
            {
                markDirty();
            }

            public boolean isItemValid(int slot, @Nonnull ItemStack stack)
            {
                switch (slot)
                {
                    case 0: return stack.getItem() == ModItems.OBSIDIAN_HAMMER.get();
                    case 1: return stack.getItem() == Items.DIAMOND;
                    case 2: return stack.getItem() == ModItems.DIAMOND_SHARD.get();
                    default: return false;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
            {
                if(!isItemValid(slot, stack))
                {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    public <T> LazyOptional getCapability(@Nonnull Capability<T> capability, @Nullable Direction side)
    {
        if( capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return handler.cast();
        }

        return super.getCapability(capability, side);
    }

}
