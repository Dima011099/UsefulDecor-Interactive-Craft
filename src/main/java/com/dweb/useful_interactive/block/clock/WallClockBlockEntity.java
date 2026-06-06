package com.dweb.useful_interactive.block.clock;

import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A lightweight block entity for the Wall Clock.
 * Does not require server-side ticking as rendering interpolation 
 * is handled entirely client-side using world day time.
 */
public class WallClockBlockEntity extends BlockEntity {
    
    public WallClockBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntites.WALL_CLOCK, pos, state);
    }
}