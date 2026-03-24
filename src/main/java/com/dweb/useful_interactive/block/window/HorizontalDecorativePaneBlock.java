package com.dweb.useful_interactive.block.window;

import javax.swing.text.html.BlockView;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class HorizontalDecorativePaneBlock extends HorizontalDirectionalBlock{
    public static final MapCodec<HorizontalPaneBlock> CODEC = simpleCodec(HorizontalPaneBlock::new);

    protected static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0);//7-5 8-10
    protected static final VoxelShape WEST_SHAPE  = Block.box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0);//7-5 8-10
    protected static final VoxelShape EAST_SHAPE = Block.box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public HorizontalDecorativePaneBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return FacingShape(state);
    }

    private VoxelShape FacingShape(BlockState state){
        Direction direction = state.getValue(FACING);
        switch (direction) {
            case Direction.NORTH: return NORTH_SHAPE;
            case Direction.SOUTH: return SOUTH_SHAPE;
            case Direction.EAST: return EAST_SHAPE;
            default: return WEST_SHAPE;
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return FacingShape(state);
    }
  
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }
}
