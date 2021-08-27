package com.mapleman.maplemod.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;


public class SapSpout extends Block
{

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = VoxelShapes.or(
            Block.makeCuboidShape(7.61, 10.14, 13.705, 8.39, 10.92, 17.8),
            Block.makeCuboidShape(7.22, 7.0200000000000005, 11.56, 8.78, 7.800000000000001, 17.8),
            Block.makeCuboidShape(8.78, 7.800000000000001, 11.56, 9.56, 9.36, 17.8),
            Block.makeCuboidShape(6.4399999999999995, 7.800000000000001, 11.56, 7.22, 9.36, 17.8),
            Block.makeCuboidShape(7.22, 9.36, 13.120000000000001, 8.78, 10.14, 17.8)
    ).simplify();

    private static final VoxelShape SHAPE_E = VoxelShapes.or(
            Block.makeCuboidShape(-1.868000000000002, 10.14, 7.678000000000001, 2.2270000000000003, 10.92, 8.458),
            Block.makeCuboidShape(-1.868000000000002, 7.0200000000000005, 7.2879999999999985, 4.372, 7.800000000000001, 8.847999999999999),
            Block.makeCuboidShape(-1.868000000000002, 7.800000000000001, 8.847999999999999, 4.372, 9.36, 9.628),
            Block.makeCuboidShape(-1.868000000000002, 7.800000000000001, 6.507999999999999, 4.372, 9.36, 7.2879999999999985),
            Block.makeCuboidShape(-1.868000000000002, 9.36, 7.2879999999999985, 2.8119999999999994, 10.14, 8.847999999999999)
    ).simplify();

    private static final VoxelShape SHAPE_S = VoxelShapes.or(
            Block.makeCuboidShape(7.542, 10.14, -1.868000000000002, 8.322, 10.92, 2.2270000000000003),
            Block.makeCuboidShape(7.152000000000001, 7.0200000000000005, -1.868000000000002, 8.712000000000002, 7.800000000000001, 4.372),
            Block.makeCuboidShape(6.372, 7.800000000000001, -1.868000000000002, 7.152000000000001, 9.36, 4.372),
            Block.makeCuboidShape(8.712000000000002, 7.800000000000001, -1.868000000000002, 9.492, 9.36, 4.372),
            Block.makeCuboidShape(7.152000000000001, 9.36, -1.868000000000002, 8.712000000000002, 10.14, 2.8119999999999994)
    ).simplify();

    private static final VoxelShape SHAPE_W = VoxelShapes.or(
            Block.makeCuboidShape(13.773, 10.14, 7.542, 17.868000000000002, 10.92, 8.322),
            Block.makeCuboidShape(11.628, 7.0200000000000005, 7.152000000000001, 17.868000000000002, 7.800000000000001, 8.712000000000002),
            Block.makeCuboidShape(11.628, 7.800000000000001, 6.372, 17.868000000000002, 9.36, 7.152000000000001),
            Block.makeCuboidShape(11.628, 7.800000000000001, 8.712000000000002, 17.868000000000002, 9.36, 9.492),
            Block.makeCuboidShape(13.188, 9.36, 7.152000000000001, 17.868000000000002, 10.14, 8.712000000000002)
    ).simplify();

    public SapSpout(Properties properties)
    {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        switch (state.get(FACING))
        {
            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot)
    {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn)
    {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState stateOpposite = worldIn.getBlockState(blockPos);

        return stateOpposite.isIn(ModBlocks.MAPLE_LOG.get());
    }



    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        BlockPos blockUnder = pos.down();
        BlockState stateBarrel = worldIn.getBlockState(blockUnder);
        ItemStack itemstack = player.getHeldItem(handIn);
        if (itemstack.isEmpty())
        {
            if(stateBarrel.isIn(ModBlocks.SYRUP_BARREL.get()))
            {
                worldIn.setBlockState(pos, state.with(SyrupBarrel.LEVEL, 1), 1);

            }
            return ActionResultType.PASS;
        } else {
            return ActionResultType.PASS;
        }
    }
}
