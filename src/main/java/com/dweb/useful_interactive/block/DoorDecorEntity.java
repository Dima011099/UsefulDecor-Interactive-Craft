package com.dweb.useful_interactive.block;

import com.dweb.useful_interactive.blockentites.ModBlockEntites;
import com.dweb.useful_interactive.util.ILockableManager;
import com.dweb.useful_interactive.util.LockComponent;
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
        //return BlockEntityUpdateS2CPacket.create(this);
        // При создании пакета мы указываем, какие данные в него положить
        return BlockEntityUpdateS2CPacket.create(this, (be, registries) -> {
        NbtCompound nbt = new NbtCompound();
        // Вручную записываем состояние замка для клиента
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
     
        // Читаем строку с дефолтным значением
        lock.bindKey(view.read("locked_key", Codec.STRING).orElse(null));
        lock.setLocked(view.read("is_locked", Codec.BOOL).orElse(false));
        super.readData(view);
    }

}