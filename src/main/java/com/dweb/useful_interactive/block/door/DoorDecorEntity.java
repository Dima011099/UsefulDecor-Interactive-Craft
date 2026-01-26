package com.dweb.useful_interactive.block.door;

import com.dweb.useful_interactive.core.lock.ILockableManager;
import com.dweb.useful_interactive.domain.lock.LockComponent;
import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;

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
    protected void writeData(WriteView view) {
        super.writeData(view);
        if(lock.hasKey())
            view.put("locked_key", Codec.STRING, lock.getKey());

        view.put("is_locked", Codec.BOOL, lock.isLocked());
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this, (be, registries) -> {
        NbtCompound nbt = new NbtCompound();
        nbt.putBoolean("is_locked", ((DoorDecorEntity)be).isLocked());
        return nbt;
    });
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }

    @Override
    protected void readData(ReadView view) {
        lock.bindKey(view.read("locked_key", Codec.STRING).orElse(null));
        lock.setLocked(view.read("is_locked", Codec.BOOL).orElse(false));
        super.readData(view);
    }
}