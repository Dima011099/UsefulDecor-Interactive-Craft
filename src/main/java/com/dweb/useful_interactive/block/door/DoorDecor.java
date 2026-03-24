package com.dweb.useful_interactive.block.door;

import javax.swing.text.html.BlockView;

import com.dweb.useful_interactive.core.lock.ILockableManager;
import com.dweb.useful_interactive.domain.lock.LockableManager;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;



public class DoorDecor extends DoorBlock implements EntityBlock { //BlockEntityProvider

    public DoorDecor(BlockSetType blockSetType, BlockBehaviour.Properties settings) {
        super(blockSetType, settings);
    }

     // В EntityBlock метод называется newBlockEntity, а не createBlockEntity
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER //get -> getValue
            ? new DoorDecorEntity(pos, state)
            : null;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) { // getRenderType -> getRenderShape public -> protected
        return RenderShape.MODEL; // BlockRenderType -> RenderShape
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (world.isClientSide()) return InteractionResult.SUCCESS;

        DoorDecorEntity door = getLowerEntity(world, pos, state);
        if (door == null) return InteractionResult.CONSUME;

        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (LockableManager.handleKeyUse(player, stack, door)){
            door.setChanged();
            world.sendBlockUpdated(
                door.getBlockPos(),
                world.getBlockState(door.getBlockPos()),
                world.getBlockState(door.getBlockPos()),
                Block.UPDATE_ALL
            );
            return InteractionResult.CONSUME;
        }

        if (door.isLocked()) {
            player.sendOverlayMessage(Component.translatable("message.useful_interactive.needs_key"));
            return InteractionResult.CONSUME;
        }

        InteractionResult result = super.useWithoutItem(state, world, pos, player, hit);
        syncOpenState(world, pos, state);

        return result;
    }

    private DoorDecorEntity getLowerEntity(Level world, BlockPos pos, BlockState state) {
        BlockPos lower = state.getValue(HALF) == DoubleBlockHalf.LOWER ? pos : pos.below();
        BlockEntity be = world.getBlockEntity(lower);
        return be instanceof DoorDecorEntity e ? e : null;
    }
 
    private void syncOpenState(Level world, BlockPos pos, BlockState state) {
        BlockPos lower = state.getValue(HALF) == DoubleBlockHalf.LOWER ? pos : pos.below();
        BlockPos upper = lower.above();

        boolean open = world.getBlockState(lower).getValue(OPEN);

        world.setBlock(upper, world.getBlockState(upper).setValue(OPEN, open), Block.UPDATE_CLIENTS);
    }

    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos) {
        float base = super.getDestroyProgress(state, player, world, pos);

        BlockPos lowerPos = state.getValue(HALF) == DoubleBlockHalf.LOWER ? pos : pos.below();
        BlockEntity be = world.getBlockEntity(lowerPos);

        if (be instanceof ILockableManager lock && lock.isLocked()) {
            return base / 30.0f;
        }
        return base;
    }
}