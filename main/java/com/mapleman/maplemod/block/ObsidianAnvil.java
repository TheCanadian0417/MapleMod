package com.mapleman.maplemod.block;

import com.mapleman.maplemod.container.AnvilContainer;
import com.mapleman.maplemod.tileentity.AnvilTile;
import com.mapleman.maplemod.tileentity.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class ObsidianAnvil extends Block
{
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_E = VoxelShapes.or(
            Block.makeCuboidShape(10, 3, 4, 10.5, 10.5, 12),
            Block.makeCuboidShape(3, 0, 2, 13, 2, 4),
            Block.makeCuboidShape(3, 0, 12, 13, 2, 14),
            Block.makeCuboidShape(6, 1, 4, 10, 10.5, 12),
            Block.makeCuboidShape(4, 1, 4, 6, 3, 6),
            Block.makeCuboidShape(4, 1, 10, 6, 3, 12),
            Block.makeCuboidShape(10, 1, 4, 12, 3, 6),
            Block.makeCuboidShape(10, 1, 10, 12, 3, 12),
            Block.makeCuboidShape(5, 1, 6, 6, 3, 10),
            Block.makeCuboidShape(10, 1, 6, 11, 3, 10),
            Block.makeCuboidShape(3, 0, 14, 13, 1, 15),
            Block.makeCuboidShape(3, 0, 1, 13, 1, 2),
            Block.makeCuboidShape(6.5, 3.5, 3, 9.5, 10.5, 4),
            Block.makeCuboidShape(6.5, 9, -0.5, 9.5, 10.5, 3),
            Block.makeCuboidShape(6.5, 7.5, 0.5, 9.5, 9, 3),
            Block.makeCuboidShape(7, 9.5, -1.5, 9, 11, -0.5),
            Block.makeCuboidShape(6, 4, 12, 10, 10.5, 12.5),
            Block.makeCuboidShape(6, 5, 12.5, 10, 10.5, 13),
            Block.makeCuboidShape(6, 6, 13, 10, 10.5, 13.5),
            Block.makeCuboidShape(6, 7, 13.5, 10, 10.5, 14),
            Block.makeCuboidShape(6, 7.5, 14, 10, 10.5, 14.5),
            Block.makeCuboidShape(6, 8, 14.5, 10, 10.5, 15),
            Block.makeCuboidShape(6, 9, 15, 10, 10.5, 15.5),
            Block.makeCuboidShape(6, 9.5, 15.5, 10, 10.5, 16),
            Block.makeCuboidShape(5.5, 3, 4, 6, 10.5, 12),
            Block.makeCuboidShape(10.5, 5, 4.5, 11, 10.5, 11.5),
            Block.makeCuboidShape(5, 5, 4.5, 5.5, 10.5, 11.5)
    ).simplify();

    private static final VoxelShape SHAPE_N = VoxelShapes.or(
            Block.makeCuboidShape(4, 3, 5.5, 12, 10.5, 6),
            Block.makeCuboidShape(2, 0, 3, 4, 2, 13),
            Block.makeCuboidShape(12, 0, 3, 14, 2, 13),
            Block.makeCuboidShape(4, 1, 6, 12, 10.5, 10),
            Block.makeCuboidShape(4, 1, 10, 6, 3, 12),
            Block.makeCuboidShape(10, 1, 10, 12, 3, 12),
            Block.makeCuboidShape(4, 1, 4, 6, 3, 6),
            Block.makeCuboidShape(10, 1, 4, 12, 3, 6),
            Block.makeCuboidShape(6, 1, 10, 10, 3, 11),
            Block.makeCuboidShape(6, 1, 5, 10, 3, 6),
            Block.makeCuboidShape(14, 0, 3, 15, 1, 13),
            Block.makeCuboidShape(1, 0, 3, 2, 1, 13),
            Block.makeCuboidShape(3, 3.5, 6.5, 4, 10.5, 9.5),
            Block.makeCuboidShape(-0.5, 9, 6.5, 3, 10.5, 9.5),
            Block.makeCuboidShape(0.5, 7.5, 6.5, 3, 9, 9.5),
            Block.makeCuboidShape(-1.5, 9.5, 7, -0.5, 11, 9),
            Block.makeCuboidShape(12, 4, 6, 12.5, 10.5, 10),
            Block.makeCuboidShape(12.5, 5, 6, 13, 10.5, 10),
            Block.makeCuboidShape(13, 6, 6, 13.5, 10.5, 10),
            Block.makeCuboidShape(13.5, 7, 6, 14, 10.5, 10),
            Block.makeCuboidShape(14, 7.5, 6, 14.5, 10.5, 10),
            Block.makeCuboidShape(14.5, 8, 6, 15, 10.5, 10),
            Block.makeCuboidShape(15, 9, 6, 15.5, 10.5, 10),
            Block.makeCuboidShape(15.5, 9.5, 6, 16, 10.5, 10),
            Block.makeCuboidShape(4, 3, 10, 12, 10.5, 10.5),
            Block.makeCuboidShape(4.5, 5, 5, 11.5, 10.5, 5.5),
            Block.makeCuboidShape(4.5, 5, 10.5, 11.5, 10.5, 11)
    ).simplify();

    private static final VoxelShape SHAPE_W = VoxelShapes.or(
            Block.makeCuboidShape(5.5, 3, 4, 6, 10.5, 12),
            Block.makeCuboidShape(3, 0, 12, 13, 2, 14),
            Block.makeCuboidShape(3, 0, 2, 13, 2, 4),
            Block.makeCuboidShape(6, 1, 4, 10, 10.5, 12),
            Block.makeCuboidShape(10, 1, 10, 12, 3, 12),
            Block.makeCuboidShape(10, 1, 4, 12, 3, 6),
            Block.makeCuboidShape(4, 1, 10, 6, 3, 12),
            Block.makeCuboidShape(4, 1, 4, 6, 3, 6),
            Block.makeCuboidShape(10, 1, 6, 11, 3, 10),
            Block.makeCuboidShape(5, 1, 6, 6, 3, 10),
            Block.makeCuboidShape(3, 0, 1, 13, 1, 2),
            Block.makeCuboidShape(3, 0, 14, 13, 1, 15),
            Block.makeCuboidShape(6.5, 3.5, 12, 9.5, 10.5, 13),
            Block.makeCuboidShape(6.5, 9, 13, 9.5, 10.5, 16.5),
            Block.makeCuboidShape(6.5, 7.5, 13, 9.5, 9, 15.5),
            Block.makeCuboidShape(7, 9.5, 16.5, 9, 11, 17.5),
            Block.makeCuboidShape(6, 4, 3.5, 10, 10.5, 4),
            Block.makeCuboidShape(6, 5, 3, 10, 10.5, 3.5),
            Block.makeCuboidShape(6, 6, 2.5, 10, 10.5, 3),
            Block.makeCuboidShape(6, 7, 2, 10, 10.5, 2.5),
            Block.makeCuboidShape(6, 7.5, 1.5, 10, 10.5, 2),
            Block.makeCuboidShape(6, 8, 1, 10, 10.5, 1.5),
            Block.makeCuboidShape(6, 9, 0.5, 10, 10.5, 1),
            Block.makeCuboidShape(6, 9.5, 0, 10, 10.5, 0.5),
            Block.makeCuboidShape(10, 3, 4, 10.5, 10.5, 12),
            Block.makeCuboidShape(5, 5, 4.5, 5.5, 10.5, 11.5),
            Block.makeCuboidShape(10.5, 5, 4.5, 11, 10.5, 11.5)
    ).simplify();

    private static final VoxelShape SHAPE_S = VoxelShapes.or(
            Block.makeCuboidShape(4, 3, 10, 12, 10.5, 10.5),
            Block.makeCuboidShape(12, 0, 3, 14, 2, 13),
            Block.makeCuboidShape(2, 0, 3, 4, 2, 13),
            Block.makeCuboidShape(4, 1, 6, 12, 10.5, 10),
            Block.makeCuboidShape(10, 1, 4, 12, 3, 6),
            Block.makeCuboidShape(4, 1, 4, 6, 3, 6),
            Block.makeCuboidShape(10, 1, 10, 12, 3, 12),
            Block.makeCuboidShape(4, 1, 10, 6, 3, 12),
            Block.makeCuboidShape(6, 1, 5, 10, 3, 6),
            Block.makeCuboidShape(6, 1, 10, 10, 3, 11),
            Block.makeCuboidShape(1, 0, 3, 2, 1, 13),
            Block.makeCuboidShape(14, 0, 3, 15, 1, 13),
            Block.makeCuboidShape(12, 3.5, 6.5, 13, 10.5, 9.5),
            Block.makeCuboidShape(13, 9, 6.5, 16.5, 10.5, 9.5),
            Block.makeCuboidShape(13, 7.5, 6.5, 15.5, 9, 9.5),
            Block.makeCuboidShape(16.5, 9.5, 7, 17.5, 11, 9),
            Block.makeCuboidShape(3.5, 4, 6, 4, 10.5, 10),
            Block.makeCuboidShape(3, 5, 6, 3.5, 10.5, 10),
            Block.makeCuboidShape(2.5, 6, 6, 3, 10.5, 10),
            Block.makeCuboidShape(2, 7, 6, 2.5, 10.5, 10),
            Block.makeCuboidShape(1.5, 7.5, 6, 2, 10.5, 10),
            Block.makeCuboidShape(1, 8, 6, 1.5, 10.5, 10),
            Block.makeCuboidShape(0.5, 9, 6, 1, 10.5, 10),
            Block.makeCuboidShape(0, 9.5, 6, 0.5, 10.5, 10),
            Block.makeCuboidShape(4, 3, 5.5, 12, 10.5, 6),
            Block.makeCuboidShape(4.5, 5, 10.5, 11.5, 10.5, 11),
            Block.makeCuboidShape(4.5, 5, 5, 11.5, 10.5, 5.5)
    ).simplify();

    public ObsidianAnvil(Properties properties)
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
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return ModTileEntities.ANVIL_TILE_ENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote())
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof AnvilTile)
            {
                INamedContainerProvider containerProvider = new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("screen.maplemod.anvil");
                    }

                    @Nullable
                    @Override
                    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                        return new AnvilContainer(i, worldIn, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
            }
            else
            {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return ActionResultType.SUCCESS;
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
}
