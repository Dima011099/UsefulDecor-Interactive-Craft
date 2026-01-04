package com.dweb.useful_interactive.common;

import net.minecraft.block.BlockState;

import net.minecraft.world.BlockView;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;

public class HorizontalPaneBlock extends HorizontalFacingBlock {
    public static final MapCodec<HorizontalPaneBlock> CODEC = createCodec(HorizontalPaneBlock::new);

    protected static final VoxelShape NORTH_SOUTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 7.5, 16.0, 16.0, 8.5);//7-5 8-10
    protected static final VoxelShape EAST_WEST_SHAPE = Block.createCuboidShape(7.5, 0.0, 0.0, 8.5, 16.0, 16.0);

    public HorizontalPaneBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

   @Override
   protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return (direction == Direction.NORTH || direction == Direction.SOUTH) ? NORTH_SOUTH_SHAPE : EAST_WEST_SHAPE;
   }

   @Override
   protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      //  return this.getOutlineShape(state, world, pos, context);
     // return super.getCollisionShape(state, world, pos, context);
     return Block.createCuboidShape(0f, 0f, 0f, 16f, 16, 16f);
   }
  
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }
}
