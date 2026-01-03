package com.dweb.useful_interactive.block;

import com.dweb.useful_interactive.util.ILockableManager;
import com.dweb.useful_interactive.util.LockComponent;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;


public class BoxBlockEntity extends LootableContainerBlockEntity implements ILockableManager {
    
    // Инвентарь на 27 слотов (как в обычном сундуке)
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);
    private final LockComponent lock = new LockComponent();


  private static final Codec<DefaultedList<ItemStack>> INVENTORY_CODEC = 
    ItemStack.OPTIONAL_CODEC.listOf().xmap(
        list -> {
            DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(27, ItemStack.EMPTY);
            for (int i = 0; i < Math.min(list.size(), 27); i++) {
                defaultedList.set(i, list.get(i));
            }
            return defaultedList;
        },
        // При записи превращаем DefaultedList обратно в List
        java.util.ArrayList::new 
    );


    public BoxBlockEntity(BlockPos pos, BlockState state) {
        super(Box.BOX_BLOCK_ENTITY_TYPE, pos, state);
    }
    /*****  */

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


/***** */

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.dima.box");
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.inventory;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
        markDirty();
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        // Создаем стандартный экран 9x3
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    @Override
    public int size() {
        return 27;
    }

  @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        // Записываем строку напрямую через ключ
        if(lock.hasKey())
            view.put("BoxKey", Codec.STRING, lock.getKey());

        view.put("Inventory", INVENTORY_CODEC, this.inventory);
        view.put("closed", Codec.BOOL, lock.isLocked());
        
        // Инвентарь в LootableContainerBlockEntity обычно сохраняется через super.writeData,
        // но если вы используете кастомный список, его тоже можно записать здесь.  DefaultedList<ItemStack>
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
    protected void readData(ReadView view) {
     
        // Читаем строку с дефолтным значением
        lock.bindKey(view.read("BoxKey", Codec.STRING).orElse(null));
        lock.setLocked(view.read("closed", Codec.BOOL).orElse(false));

        this.inventory = view.read("Inventory", INVENTORY_CODEC)
                         .orElse(DefaultedList.ofSize(27, ItemStack.EMPTY));
        super.readData(view);
    }


}
