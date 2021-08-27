package com.mapleman.maplemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
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
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_0_3;

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


}
