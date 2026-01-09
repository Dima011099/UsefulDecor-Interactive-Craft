package com.dweb.useful_interactive.common;

import com.dweb.useful_interactive.registry.items.KeyItem;
import com.dweb.useful_interactive.registry.ui.ModScreenHandlers;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;

public class KeyCabinetScreenHandler extends ScreenHandler {
    private final CraftingInventory inputInventory = new CraftingInventory(this, 3, 2); 
    private final CraftingResultInventory resultInventory = new CraftingResultInventory();
    private final Inventory inventory;

    public KeyCabinetScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(9)); // 9 слотов для ключей
    }

    public KeyCabinetScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.KEY_CABINET, syncId);
        checkSize(inventory, 9);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        
          this.addSlot(new CraftingResultSlot(playerInventory.player, inputInventory, resultInventory, 0, 134, 47));

     this.addSlot(new Slot(inputInventory, 1, 26, 47));
        this.addSlot(new Slot(inputInventory, 2, 52, 47));
        this.addSlot(new Slot(inputInventory, 3, 76, 47));

  
        this.addSlot(new SpecificItemSlot(inputInventory, 0, 52, 22));

        // Инвентарь игрока
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // Хотбар
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
       
        
        return ItemStack.EMPTY; 
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}



class SpecificItemSlot extends Slot {
    public SpecificItemSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        // Разрешаем вставку, только если предмет совпадает с ALLOWED_ITEM
        return stack.getItem() == KeyItem.MY_ITEM;
    }
}

