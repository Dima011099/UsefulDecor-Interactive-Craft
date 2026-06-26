package com.dweb.useful_interactive.block.clock;

import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/**
 * A lightweight block entity for the Wall Clock.
 * Does not require server-side ticking as rendering interpolation 
 * is handled entirely client-side using world day time.
 */
@SuppressWarnings("null")
public class WallClockBlockEntity extends BlockEntity {
    private boolean isAlarmSet = false; 
    private int alarmTime = 0;

    public WallClockBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntites.WALL_CLOCK, pos, state);
    }

 
    public int getAlarmTime() {
        return this.alarmTime;
    }

    public boolean getIsAlarmSet(){
        return this.isAlarmSet;
    }
    public void setIsAlarmSet(boolean value){
        this.isAlarmSet = value;
    }

    public void incrementAlarmTime() {
        this.alarmTime = (this.alarmTime + 1000) % 24000;
        this.setChanged();
    }


public static void tick(Level world, BlockPos pos, BlockState state, WallClockBlockEntity blockEntity) {
    long worldTime = blockEntity.getLevel().getDefaultClockTime();
    long alarm = blockEntity.getAlarmTime();

    long timePassed = (worldTime - alarm + 24000L) % 24000L;

 
    if (blockEntity.getIsAlarmSet() && timePassed >= 0 && timePassed < 1000) {
        if (!state.getValue(WallClockBlock.POWERED)) {
            world.setBlock(pos, state.setValue(WallClockBlock.POWERED, true), Block.UPDATE_ALL);
        }
        if (worldTime % 10L == 0L) {
            world.playSound(null, pos, SoundEvents.NOTE_BLOCK_BELL.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        
    }else{
            if (state.getValue(WallClockBlock.POWERED)) {
                world.setBlock(pos, state.setValue(WallClockBlock.POWERED, false), Block.UPDATE_ALL);
            }
    }
}

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("AlarmTime", this.alarmTime);
        output.putBoolean("isAlarm", this.isAlarmSet);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.alarmTime = input.getIntOr("AlarmTime", 0);
        this.isAlarmSet = input.getBooleanOr("isAlarm", true);
    }
}