package com.dweb.useful_interactive.block.chest;

import com.dweb.useful_interactive.core.lock.ILockableManager;
import com.dweb.useful_interactive.domain.lock.LockComponent;
import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
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
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }

    @Override
    protected void loadAdditional(ValueInput view) {
        lock.bindKey(view.read("BoxKey", Codec.STRING).orElse(null));
        lock.setLocked(view.read("closed", Codec.BOOL).orElse(false));

        this.inventory = view.read("Inventory", INVENTORY_CODEC)
                         .orElse(DefaultedList.ofSize(27, ItemStack.EMPTY));
        super.loadAdditional(view);
    }
}
