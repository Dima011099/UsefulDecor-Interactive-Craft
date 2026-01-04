package com.dweb.useful_interactive.block;

import com.dweb.useful_interactive.util.LockableManager;
import com.mojang.serialization.MapCodec;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;


public class BoxBlock extends BlockWithEntity {
    public static final MapCodec<BoxBlock> CODEC = createCodec(BoxBlock::new);

    @Override
    public MapCodec<? extends BoxBlock> getCodec() {
        return CODEC;
    }

    public BoxBlock(Settings settings) {
        super(settings);
         this.setDefaultState(this.stateManager.getDefaultState().with(FacingBlock.FACING, Direction.NORTH));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.SUCCESS;

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof BoxBlockEntity chest)) 
            return ActionResult.CONSUME;

        ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);
        
        if (LockableManager.handleKeyUse(player, stack, chest)){
            chest.markDirty();
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
            return ActionResult.CONSUME;
        }

        if (chest.isLocked()) {
            player.sendMessage(Text.translatable("message.useful_interactive.needs_key"), true);
            return ActionResult.CONSUME;
        }

        NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
        if (screenHandlerFactory != null) {
            player.openHandledScreen(screenHandlerFactory);
        }
        return ActionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BoxBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        float defaultDelta = super.calcBlockBreakingDelta(state, player, world, pos);
        
        BoxBlockEntity chest = (BoxBlockEntity) world.getBlockEntity(pos);
        if(chest.isLocked()){
            return defaultDelta / 30.0f;
        }
        return defaultDelta;
    }

 @Override
 protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
    BlockEntity blockEntity = world.getBlockEntity(pos);
    
    if (blockEntity instanceof BoxBlockEntity boxEntity) {
        ItemScatterer.spawn(world, pos, boxEntity);
        world.updateComparators(pos, state.getBlock());
    }
    super.onStateReplaced(state, world, pos, moved);
 }

 @Override
 protected void appendProperties(Builder<Block, BlockState> builder) {
    builder.add(FacingBlock.FACING);
    super.appendProperties(builder);
 }

 @Override
 public @org.jspecify.annotations.Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
    return this.getDefaultState().with(FacingBlock.FACING, ctx.getHorizontalPlayerFacing().getOpposite());
 }
}
