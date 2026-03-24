package com.dweb.useful_interactive.block.door;

import com.dweb.useful_interactive.core.lock.ILockableManager;
import com.dweb.useful_interactive.domain.lock.LockComponent;
import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class DoorDecorEntity extends BlockEntity implements ILockableManager {
    private final LockComponent lock = new LockComponent();

    public DoorDecorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntites.DOOR_BLOCK_ENTITY_TYPE,pos, state);
    }

    @Override
    public boolean isLocked() {
        return lock.isLocked();
    }

    @Override
    public boolean tryUnlock(String key) {
        return lock.tryUnlock(key);
    }

    @Override
    public void bindKey(String key) {
        lock.bindKey(key);
    }

    @Override
    public boolean hasKey() {
        return lock.hasKey();
    }

     @Override
    protected void saveAdditional(ValueOutput view) {//writeData(null);
        super.saveAdditional(view);
        if(lock.hasKey())
            view.putString("locked_key", lock.getKey());

        view.putBoolean("is_locked", lock.isLocked());
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, (be, registries) -> {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("is_locked", ((DoorDecorEntity)be).isLocked());
        return nbt;
    });
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        //return createNbt(registries);
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("is_locked", this.isLocked());
        tag.putString("locked_key", lock.getKey());
        return tag;
    }

    @Override
    protected void loadAdditional(ValueInput view) {//readData
        lock.bindKey(view.read("locked_key", Codec.STRING).orElse(null));
        lock.setLocked(view.read("is_locked", Codec.BOOL).orElse(false));
        super.loadAdditional(view);
    }
}