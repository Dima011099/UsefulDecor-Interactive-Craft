package com.dweb.useful_interactive.block.window;


import javax.swing.text.html.BlockView;

import com.jcraft.jorbis.Block;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;



public class HorizontalPaneBlock extends HorizontalDirectionalBlock {//HorizontalFacingBlock
    public static final MapCodec<HorizontalPaneBlock> CODEC = createCodec(HorizontalPaneBlock::new);

    protected static final VoxelShape NORTH_SOUTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 7.5, 16.0, 16.0, 8.5);//7-5 8-10
    protected static final VoxelShape EAST_WEST_SHAPE = Block.createCuboidShape(7.5, 0.0, 0.0, 8.5, 16.0, 16.0);

    public HorizontalPaneBlock(BlockBehaviour.Properties settings) {
        super(settings);
        //setDefaultState
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));//this.stateManager.getDefaultState() with -> setValue
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return (direction == Direction.NORTH || direction == Direction.SOUTH) ? NORTH_SOUTH_SHAPE : EAST_WEST_SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
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
