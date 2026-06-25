package com.dweb.useful_interactive.block.clock;


import org.jspecify.annotations.Nullable;

import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


/**
 * A wall-mounted clock block that triggers a redstone signal and sound notification
 * when the world day time matches the configured alarm time.
 * Handles user interactions for setting and disabling the alarm state.
 */
@SuppressWarnings("null")
public class WallClockBlock extends HorizontalDirectionalBlock implements EntityBlock {
    
    public static final MapCodec<WallClockBlock> CODEC = simpleCodec(WallClockBlock::new);

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
  
    protected static final VoxelShape NORTH_SHAPE = Block.box(3.0, 3.0, 15.0, 13.0, 13.0, 16.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(3.0, 3.0, 0.0, 13.0, 13.0, 1.0);
    protected static final VoxelShape EAST_SHAPE = Block.box(0.0, 3.0, 3.0, 1.0, 13.0, 13.0);
    protected static final VoxelShape WEST_SHAPE = Block.box(15.0, 3.0, 3.0, 16.0, 13.0, 13.0);

    public WallClockBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        registerDefaultState(this.stateDefinition.any().setValue(POWERED, false));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new WallClockBlockEntity(worldPosition, blockState);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return facingShape(state); 
    }

@Nullable
@Override
public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
    if (type == ModBlockEntites.WALL_CLOCK) {
        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof WallClockBlockEntity clockEntity) {
                WallClockBlockEntity.tick(world1, pos, state1, clockEntity);
            }
        };
    }
    return null;
}


   @Override
protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    if (level.getBlockEntity(pos) instanceof WallClockBlockEntity clockEntity) {
        if (!level.isClientSide()) {

            if (player.isShiftKeyDown()) {
                clockEntity.setIsAlarmSet(false);
                clockEntity.setChanged();
                
                if (state.getValue(POWERED)) {
                    level.setBlock(pos, state.setValue(POWERED, false), Block.UPDATE_ALL);
                }
                
                level.playSound(null, pos, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundSource.BLOCKS, 0.8F, 0.6F);
                player.sendOverlayMessage(Component.literal("Будильник ПОЛНОСТЬЮ ОТКЛЮЧЕН!"));
                return InteractionResult.SUCCESS;
            }else{
            clockEntity.incrementAlarmTime();
            
            int displayHour = (int) ((clockEntity.getAlarmTime() / 1000) + 6) % 24;
            player.sendOverlayMessage(Component.literal("Будильник: " + displayHour + ":00"));
            clockEntity.setIsAlarmSet(true);
            }
        }
       return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;

    }
    
    return InteractionResult.PASS; 
}

    @Override
    protected boolean isSignalSource(BlockState state) { return true; }

    @Override
    protected int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        if (state.getValue(POWERED)) { 
            return 15; 
        }
        return 0;
    }


    private VoxelShape facingShape(BlockState state){
        Direction direction = state.getValue(FACING);
        return switch (direction) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            default -> WEST_SHAPE;
        }; 
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return facingShape(state);
    }
  
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
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