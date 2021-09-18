package com.mapleman.maplemod.block;

import com.mapleman.maplemod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;


public class SyrupBarrel extends Block
{
    private static final VoxelShape BARREL = VoxelShapes.or(
            Block.makeCuboidShape(2, 0, 2, 14, 1, 14),
            Block.makeCuboidShape(1, 0, 2, 2, 17, 14),
            Block.makeCuboidShape(14, 0, 2, 15, 17, 14),
            Block.makeCuboidShape(2, 0, 14, 14, 17, 15),
            Block.makeCuboidShape(2, 0, 1, 14, 17, 2),
            Block.makeCuboidShape(2, 2, 0, 14, 4, 1),
            Block.makeCuboidShape(14, 2, 1, 15, 4, 2),
            Block.makeCuboidShape(15, 2, 2, 16, 4, 14),
            Block.makeCuboidShape(14, 2, 14, 15, 4, 15),
            Block.makeCuboidShape(1, 2, 14, 2, 4, 15),
            Block.makeCuboidShape(1, 2, 1, 2, 4, 2),
            Block.makeCuboidShape(0, 2, 2, 1, 4, 14),
            Block.makeCuboidShape(2, 2, 15, 14, 4, 16),
            Block.makeCuboidShape(14, 13, 14, 15, 15, 15),
            Block.makeCuboidShape(15, 13, 2, 16, 15, 14),
            Block.makeCuboidShape(14, 13, 1, 15, 15, 2),
            Block.makeCuboidShape(2, 13, 0, 14, 15, 1),
            Block.makeCuboidShape(1, 13, 1, 2, 15, 2),
            Block.makeCuboidShape(0, 13, 2, 1, 15, 14),
            Block.makeCuboidShape(1, 13, 14, 2, 15, 15),
            Block.makeCuboidShape(2, 13, 15, 14, 15, 16)
    ).simplify();

    public SyrupBarrel (Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(LEVEL, Integer.valueOf(0)));
    }
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_0_8;

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return BARREL;
    }

    public void setWaterLevel(World worldIn, BlockPos pos, BlockState state, int level)
    {
        worldIn.setBlockState(pos, state.with(LEVEL, Integer.valueOf(MathHelper.clamp(level, 0, 3))), 2);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        ItemStack itemstack = player.getHeldItem(handIn);
        Item item = itemstack.getItem();
        int i = state.get(LEVEL);

        if (item == Items.BUCKET) {
            if (i == 3) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 2), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(ModItems.MAPLE_SAP_BUCKET.get()));
            } else if (i == 2) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 1), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(ModItems.MAPLE_SAP_BUCKET.get()));
            } else if (i == 1) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 0), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(ModItems.MAPLE_SAP_BUCKET.get()));
            } else if (i == 6) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 5), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(ModItems.MAPLE_SYRUP_BUCKET.get()));
            } else if (i == 5) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 4), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(ModItems.MAPLE_SYRUP_BUCKET.get()));
            } else if (i == 4) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 0), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(ModItems.MAPLE_SYRUP_BUCKET.get()));
            }

        } else if (item == ModItems.MAPLE_SAP_BUCKET.get()) {
            if (i == 0) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 1), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
            } else if (i == 1) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 2), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
            } else if (i == 2) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 3), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
            }

        } else if (item == ModItems.MAPLE_SYRUP_BUCKET.get()) {
            if (i == 0) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 4), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
            } else if (i == 4) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 5), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
            } else if (i == 5) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 6), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
            }

        } else if (item == Items.DIAMOND_SWORD) {
            if (i == 6) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 0), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(ModItems.MAPLE_DIPPED_SWORD.get()));
            }

        } else if (item == Items.DIAMOND_AXE) {
            if (i == 6) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 0), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(ModItems.MAPLE_DIPPED_AXE.get()));
            }

        } else if (item == Items.DIAMOND_PICKAXE) {
            if (i == 6) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 0), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(ModItems.MAPLE_DIPPED_PICKAXE.get()));
            }

        } else if (item == Items.DIAMOND_SHOVEL) {
            if (i == 6) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(SyrupBarrel.LEVEL, 0), 1);
                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.shrink(1);
                player.setHeldItem(handIn, new ItemStack(ModItems.MAPLE_DIPPED_SHOVEL.get()));
            }

    } else {
            return ActionResultType.PASS;
        }
        return ActionResultType.PASS;
    }
}
