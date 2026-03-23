package com.dweb.useful_interactive.block.door;

import javax.swing.text.html.BlockView;

import com.dweb.useful_interactive.core.lock.ILockableManager;
import com.dweb.useful_interactive.domain.lock.LockableManager;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
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
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.SUCCESS;

        DoorDecorEntity door = getLowerEntity(world, pos, state);
        if (door == null) return ActionResult.CONSUME;

        ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);

        if (LockableManager.handleKeyUse(player, stack, door)){
            door.markDirty();
            world.updateListeners(
                door.getPos(),
                world.getBlockState(door.getPos()),
                world.getBlockState(door.getPos()),
                Block.NOTIFY_ALL
            );
            return ActionResult.CONSUME;
        }

        if (door.isLocked()) {
            player.sendMessage(Text.translatable("message.useful_interactive.needs_key"), true);
            return ActionResult.CONSUME;
        }

        ActionResult result = super.onUse(state, world, pos, player, hit);
        syncOpenState(world, pos, state);

        return result;
    }

    private DoorDecorEntity getLowerEntity(World world, BlockPos pos, BlockState state) {
        BlockPos lower = state.get(HALF) == DoubleBlockHalf.LOWER ? pos : pos.down();
        BlockEntity be = world.getBlockEntity(lower);
        return be instanceof DoorDecorEntity e ? e : null;
    }
 
    private void syncOpenState(World world, BlockPos pos, BlockState state) {
        BlockPos lower = state.get(HALF) == DoubleBlockHalf.LOWER ? pos : pos.down();
        BlockPos upper = lower.up();

        boolean open = world.getBlockState(lower).get(OPEN);

        world.setBlockState(upper, world.getBlockState(upper).with(OPEN, open), Block.NOTIFY_LISTENERS);
    }

    @Override
    protected float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        float base = super.calcBlockBreakingDelta(state, player, world, pos);

        BlockPos lowerPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos : pos.down();
        BlockEntity be = world.getBlockEntity(lowerPos);

        if (be instanceof ILockableManager lock && lock.isLocked()) {
            return base / 30.0f;
        }
        return base;
    }
}