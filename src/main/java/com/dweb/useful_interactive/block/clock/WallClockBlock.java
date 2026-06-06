package com.dweb.useful_interactive.block.clock;

import org.jspecify.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class WallClockBlock extends HorizontalDirectionalBlock implements EntityBlock {
    
    // ИСПРАВЛЕНО: Кодек теперь строго привязан к WallClockBlock, игра больше не вылетит
    public static final MapCodec<WallClockBlock> CODEC = simpleCodec(WallClockBlock::new);
  
    protected static final VoxelShape NORTH_SHAPE = Block.box(3.0, 3.0, 15.0, 13.0, 13.0, 16.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(3.0, 3.0, 0.0, 13.0, 13.0, 1.0);
    protected static final VoxelShape EAST_SHAPE = Block.box(0.0, 3.0, 3.0, 1.0, 13.0, 13.0);
    protected static final VoxelShape WEST_SHAPE = Block.box(15.0, 3.0, 3.0, 16.0, 13.0, 13.0);

    public WallClockBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new WallClockBlockEntity(worldPosition, blockState);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return facingShape(state); // Исправлен регистр названия метода (FacingShape -> facingShape)
    }

    // Название метода переведено в правильный camelCase (маленькая буква в начале)
    private VoxelShape facingShape(BlockState state){
        Direction direction = state.getValue(FACING);
        return switch (direction) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            default -> WEST_SHAPE;
        }; // Старый switch заменен на лаконичный стрелочный Java 25 switch-expression
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return facingShape(state);
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