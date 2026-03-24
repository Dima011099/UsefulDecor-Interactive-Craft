package com.dweb.useful_interactive.block.chest;

import com.dweb.useful_interactive.domain.lock.LockableManager;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.swing.text.html.BlockView;

import org.jetbrains.annotations.Nullable;

public class BoxBlock extends BaseEntityBlock {//BlockWithEntity
    public static final MapCodec<BoxBlock> CODEC = simpleCodec(BoxBlock::new);
       public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    @Override
    public MapCodec<? extends BoxBlock> codec() {
        return CODEC;
    }

    public BoxBlock(Properties settings) {
        super(settings);
         this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));// HorizontalDirectionalBlock.FACING
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (world.isClientSide()) return InteractionResult.SUCCESS;

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof BoxBlockEntity chest)) 
            return InteractionResult.CONSUME;

        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        
        if (LockableManager.handleKeyUse(player, stack, chest)){
            chest.setChanged();
            world.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
            return InteractionResult.CONSUME;
        }

        if (chest.isLocked()) {
            player.sendOverlayMessage(Component.translatable("message.useful_interactive.needs_key"));
            return InteractionResult.CONSUME;
        }

        MenuProvider screenHandlerFactory = state.getMenuProvider(world, pos);
        if (screenHandlerFactory != null) {
            player.openMenu(screenHandlerFactory);
        }
        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BoxBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos) {
        float defaultDelta = super.getDestroyProgress(state, player, world, pos);
        
        BoxBlockEntity chest = (BoxBlockEntity) world.getBlockEntity(pos);
        if(chest.isLocked()){
            return defaultDelta / 30.0f;
        }
        return defaultDelta;
    }

    @Override
    protected void onStateReplaced(BlockState state, Level world, BlockPos pos, boolean moved) {//onStateReplaced
        BlockEntity blockEntity = world.getBlockEntity(pos);
    
        if (blockEntity instanceof BoxBlockEntity boxEntity) {
            Containers.dropContents(world, pos, boxEntity);//ItemScatterer
            world.updateNeighborsAt(pos, state.getBlock());//updateComparators
        }
        super.onStateReplaced(state, world, pos, moved);
    }


    

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public @org.jspecify.annotations.Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }
}
