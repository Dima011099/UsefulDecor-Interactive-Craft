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
import net.minecraft.item.Items;
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

    
   private RecipeInput input;

   private boolean isCrafting = false;
private ItemStack lastInputSnapshot = ItemStack.EMPTY;

 public KeyCabinetScreenHandler(int syncId, PlayerInventory playerInventory) {
    this(syncId, playerInventory, new SimpleInventory(4), ScreenHandlerContext.EMPTY);
}



    public KeyCabinetScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, ScreenHandlerContext context) {
        super(ModScreenHandlers.KEY_CABINET, syncId);
        this.context = context;

        inventory.onOpen(playerInventory.player);

        

    
     

   this.addSlot(new CraftingResultSlot(playerInventory.player, inputInventory, resultInventory, 0, 134, 47){
    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        ItemStack itemStack = inputInventory.getStack(0).copy();

        consumeIngredients(input);
        stack.getItem().onCraft(stack, player.getEntityWorld());

        inputInventory.setStack(0, itemStack);
        if(itemStack != ItemStack.EMPTY && itemStack.getItem() == KeyItem.MY_ITEM)
            stack.set(ModComponentType.KEY_F_CLOSE, itemStack.get(ModComponentType.KEY_F_CLOSE));
        updateToClient();
        sendContentUpdates();
    }
   });

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
    
    // Предотвращаем рекурсию
    if (isCrafting) return;
    
    this.context.run((world, pos) -> {
        if (!(world instanceof ServerWorld serverWorld)) return;

        // Проверяем, изменились ли входные слоты
     
        ItemStack currentInput = createInputSnapshot();
        if (ItemStack.areEqual(currentInput, lastInputSnapshot)) {
            return; // Ничего не изменилось, выходим
        }

        input = new RecipeInput() {
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
            isCrafting = true; // Устанавливаем флаг
            result = match.get().value().craft(input, serverWorld.getRegistryManager());
            
            // Потребляем ингредиенты
            
            
            lastInputSnapshot = createInputSnapshot(); // Сохраняем новое состояние
            isCrafting = false; // Сбрасываем флаг
        }

        resultInventory.setStack(0, result);
        inputInventory.markDirty();
        resultInventory.markDirty();     
        this.sendContentUpdates();
        this.updateToClient();
    });
}

private ItemStack createInputSnapshot() {
    ItemStack snapshot = new ItemStack(Items.AIR, 1);

    // Пример: собираем "снимок" из всех входных слотов
    for (int i = 0; i < inputInventory.size(); i++) {
        ItemStack slotStack = inputInventory.getStack(i);
        if (!slotStack.isEmpty()) {
            snapshot = slotStack.copy();
            break;
        }
    }

    return snapshot;
}

private void consumeIngredients(RecipeInput input) {
    // Потребляем ингредиенты из входных слотов
    for (int i = 0; i < input.size(); i++) {
        ItemStack stack = input.getStackInSlot(i);
        if (!stack.isEmpty()) {
            stack.decrement(1);
        }
    }
}
}

class SpecificItemSlot extends Slot {
    public SpecificItemSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() == KeyItem.MY_ITEM;
    }
}
