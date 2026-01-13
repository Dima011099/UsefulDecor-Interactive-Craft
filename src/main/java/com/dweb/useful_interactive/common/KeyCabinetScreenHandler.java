package com.dweb.useful_interactive.common;

import java.util.Optional;

import com.dweb.useful_interactive.registry.items.KeyItem;
import com.dweb.useful_interactive.registry.ui.ModScreenHandlers;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;

public class KeyCabinetScreenHandler extends ScreenHandler {
    private final CraftingInventory inputInventory = new CraftingInventory(this, 4, 1); 
    private final CraftingResultInventory resultInventory = new CraftingResultInventory();
    private final ScreenHandlerContext context;

    
  

 public KeyCabinetScreenHandler(int syncId, PlayerInventory playerInventory) {
    this(syncId, playerInventory, new SimpleInventory(4), ScreenHandlerContext.EMPTY);
}



    public KeyCabinetScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, ScreenHandlerContext context) {
        super(ModScreenHandlers.KEY_CABINET, syncId);
        this.context = context;

        inventory.onOpen(playerInventory.player);

        

    
     

   this.addSlot(new CraftingResultSlot(playerInventory.player, inputInventory, resultInventory, 0, 134, 47));

        this.addSlot(new SpecificItemSlot(inputInventory, 0, 52, 22));
     this.addSlot(new Slot(inputInventory, 1, 26, 47));
        this.addSlot(new Slot(inputInventory, 2, 52, 47));
        this.addSlot(new Slot(inputInventory, 3, 76, 47));

    
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }


        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        return ItemStack.EMPTY; 
    }




@Override
public void onClosed(PlayerEntity player) {
    super.onClosed(player);
    
    if (!player.getEntityWorld().isClient()) {
        this.dropInventory(player, this.inputInventory);  
        this.resultInventory.clear();
    }
}


    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }


    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        
         this.context.run((world, pos) -> {
        if (!(world instanceof ServerWorld serverWorld)) return;

        RecipeInput input = new RecipeInput() {
            public int size() { return inputInventory.size(); }
                       @Override
            public ItemStack getStackInSlot(int slot) {
                return inputInventory.getStack(slot);
            }
        };

      Optional<RecipeEntry<KeyCabinetRecipe>> match = serverWorld.getRecipeManager()
                .getFirstMatch(ModRecipeTypes.KEY_CABINET, input, serverWorld);

        ItemStack result = ItemStack.EMPTY;
        if (match.isPresent()) {
            result = match.get().value().craft(input, serverWorld.getRegistryManager());

                           
        
        } else {
            result = ItemStack.EMPTY;
           
        }
        

resultInventory.setStack(0, result);

        
  inputInventory.markDirty();
  resultInventory.markDirty();     
  this.sendContentUpdates();

    });
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
