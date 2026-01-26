package com.dweb.useful_interactive.block.door;

import com.dweb.useful_interactive.core.lock.ILockableManager;
import com.dweb.useful_interactive.domain.lock.LockableManager;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class DoorDecor extends DoorBlock implements BlockEntityProvider {

    public DoorDecor(BlockSetType blockSetType, Settings settings) {
        super(blockSetType, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return state.get(HALF) == DoubleBlockHalf.LOWER
            ? new DoorDecorEntity(pos, state)
            : null;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.SUCCESS;

        DoorDecorEntity door = getLowerEntity(world, pos, state);
        if (door == null) return ActionResult.CONSUME;

        ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);

        if (LockableManager.handleKeyUse(player, stack, door)){
            door.markDirty();
           // world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
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
        /*float base = super.calcBlockBreakingDelta(state, player, world, pos);


        BlockPos lowerPos = state.get(DoorBlock.HALF) == DoubleBlockHalf.LOWER
            ? pos
            : pos.down();

        if (!(world instanceof World realWorld)) {
            return base;
        }

        BlockEntity be = realWorld.getBlockEntity(lowerPos);

        if (be instanceof ILockableManager lock && lock.isLocked()) {
            return base / 20.0f;
        }

        return base;*/
            float base = super.calcBlockBreakingDelta(state, player, world, pos);

    // Ищем нижнюю часть двери, где лежит BlockEntity
    BlockPos lowerPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos : pos.down();
    
    // Используем BlockView напрямую, он умеет доставать BlockEntity
    BlockEntity be = world.getBlockEntity(lowerPos);

    if (be instanceof ILockableManager lock && lock.isLocked()) {
        // Если закрыто, замедляем в 20 раз
        return base / 30.0f;
    }

    return base;
    }
}