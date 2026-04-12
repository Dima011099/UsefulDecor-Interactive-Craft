package com.dweb.useful_interactive.block.clock;

import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WallClockBlockEntity extends BlockEntity {
    public WallClockBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntites.WALL_CLOCK, pos, state);
    }
}
