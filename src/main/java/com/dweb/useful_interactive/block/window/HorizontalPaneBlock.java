package com.dweb.useful_interactive.block.window;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


@SuppressWarnings("null")
public class HorizontalPaneBlock extends HorizontalDirectionalBlock {
   // public static final MapCodec<HorizontalPaneBlock> CODEC = simpleCodec(HorizontalPaneBlock::new); //createCodec

    protected static final VoxelShape NORTH_SOUTH_SHAPE = Block.box(0.0, 0.0, 7.5, 16.0, 16.0, 8.5);//7-5 8-10
    protected static final VoxelShape EAST_WEST_SHAPE = Block.box(7.5, 0.0, 0.0, 8.5, 16.0, 16.0);

    public HorizontalPaneBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return (direction == Direction.NORTH || direction == Direction.SOUTH) ? NORTH_SOUTH_SHAPE : EAST_WEST_SHAPE;
    }

    @Override //BlockView ShapeContext
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(0f, 0f, 0f, 16f, 16, 16f);
    }
  
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite()); 
    }

    /*@Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }*/
}
