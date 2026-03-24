package com.dweb.useful_interactive.block.chest;

import com.dweb.useful_interactive.block.door.DoorDecorEntity;
import com.dweb.useful_interactive.core.lock.ILockableManager;
import com.dweb.useful_interactive.domain.lock.LockComponent;
import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;



public class BoxBlockEntity extends RandomizableContainerBlockEntity implements ILockableManager {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(27, ItemStack.EMPTY);
    private final LockComponent lock = new LockComponent();

    private static final Codec<NonNullList<ItemStack>> INVENTORY_CODEC = 
        ItemStack.OPTIONAL_CODEC.listOf().xmap(
            list -> {
                NonNullList<ItemStack> defaultedList = NonNullList.withSize(27, ItemStack.EMPTY);
                for (int i = 0; i < Math.min(list.size(), 27); i++) {
                    defaultedList.set(i, list.get(i));
                }
                return defaultedList;
            },
            java.util.ArrayList::new 
        );


    public BoxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntites.BOX_BLOCK_ENTITY_TYPE, pos, state);
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
    protected Component getDefaultName() {
        return Component.translatable("container.useful_interactive.box");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> inventory) {
        this.inventory = inventory;
        setChanged();
    }

    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory) {
        return ChestMenu.threeRows(syncId, playerInventory, this);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);
        if(lock.hasKey())
            view.putString("BoxKey", lock.getKey());

        view.store("Inventory", INVENTORY_CODEC, this.inventory);
        view.putBoolean("closed", lock.isLocked());
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, (be, registries) -> {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("closed", ((BoxBlockEntity)be).isLocked());
        nbt.putString("BoxKey", lock.getKey());
        nbt.store("Inventory",INVENTORY_CODEC, inventory);
        return nbt;
    });
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("is_locked", this.isLocked());
        tag.putString("locked_key", lock.getKey());
        tag.store("Inventory",INVENTORY_CODEC,  inventory);
        return tag;
    }

    @Override
    protected void loadAdditional(ValueInput view) {
        lock.bindKey(view.read("BoxKey", Codec.STRING).orElse(null));
        lock.setLocked(view.read("closed", Codec.BOOL).orElse(false));

        this.inventory = view.read("Inventory", INVENTORY_CODEC)
                         .orElse(NonNullList.withSize(27, ItemStack.EMPTY));
        super.loadAdditional(view);
    }
}
