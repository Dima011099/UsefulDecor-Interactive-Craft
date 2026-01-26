package com.dweb.useful_interactive.ui.keybox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dweb.useful_interactive.recipe.ModRecipeTypes;
import com.dweb.useful_interactive.recipe.keybox.KeyBoxRecipe;
import com.dweb.useful_interactive.registry.ModComponentType;
import com.dweb.useful_interactive.registry.items.KeyItem;
import com.dweb.useful_interactive.ui.ModScreenHandlers;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class KeyBoxScreenHandler extends ScreenHandler {
    private final CraftingInventory inputInventory = new CraftingInventory(this, 4, 1); 
    private final CraftingResultInventory resultInventory = new CraftingResultInventory();
    private final ScreenHandlerContext context;
    private RecipeInput input;
    private boolean isCrafting = false;
    private List<ItemStack> lastInputSnapshot;
    private PlayerEntity player;

    public KeyBoxScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(4), ScreenHandlerContext.EMPTY);
    }

    public KeyBoxScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, ScreenHandlerContext context) {
        super(ModScreenHandlers.KEY_CABINET, syncId);
        this.context = context;
        this.player = playerInventory.player;
        inventory.onOpen(playerInventory.player);
        lastInputSnapshot = createInputSnapshot();

        this.addSlot(new CraftingResultSlot(playerInventory.player, inputInventory, resultInventory, 0, 134, 47){
            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                ItemStack itemStack = inputInventory.getStack(0).copy();

                consumeIngredients(input);
                stack.getItem().onCraft(stack, player.getEntityWorld());

                inputInventory.setStack(0, itemStack);
                if(itemStack != ItemStack.EMPTY && itemStack.getItem() == KeyItem.MY_ITEM)
                    stack.set(ModComponentType.KEY_F_CLOSE, itemStack.get(ModComponentType.KEY_F_CLOSE));
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
    
        if (isCrafting) return;
    
        this.context.run((world, pos) -> {
            if (!(world instanceof ServerWorld serverWorld)) return;
     
            List<ItemStack> currentInput = createInputSnapshot();

            if(inputsEqual(currentInput, lastInputSnapshot)){
                return;
            }
        

            input = new RecipeInput() {
                public int size() { return inputInventory.size(); }
                @Override
                public ItemStack getStackInSlot(int slot) {
                    return inputInventory.getStack(slot);
                }
            };

            Optional<RecipeEntry<KeyBoxRecipe>> match = serverWorld.getRecipeManager()
                .getFirstMatch(ModRecipeTypes.KEY_CABINET, input, serverWorld);

            ItemStack result = ItemStack.EMPTY;
           
            isCrafting = true; 
            if (match.isPresent())
                result = match.get().value().craft(input, serverWorld.getRegistryManager());
 
            lastInputSnapshot = createInputSnapshot();
            isCrafting = false; 
        

            resultInventory.setStack(0, result);
            
            inputInventory.markDirty();
            resultInventory.markDirty();  
       
            if (player instanceof ServerPlayerEntity serverPlayer) {
                    serverPlayer.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(
                    this.syncId, 
                    this.nextRevision(), 
                0,
                    result
                ));
            }
        });
    }

    private boolean inputsEqual(List<ItemStack> a, List<ItemStack> b) {
        if (a.size() != b.size()) return false;

        for (int i = 0; i < a.size(); i++) {
            if (!ItemStack.areEqual(a.get(i), b.get(i))) {
                return false;
            }
        }
        return true;
    }

    private List<ItemStack> createInputSnapshot() {
        List<ItemStack> snapshot = new ArrayList<>();

        for (int i = 0; i < inputInventory.size(); i++) {
            snapshot.add(inputInventory.getStack(i).copy());
        }

        return snapshot;
    }

    private void consumeIngredients(RecipeInput input) {
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (!stack.isEmpty()) {
                stack.decrement(1);
            }
        }
   } 
   
    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory == this.inputInventory && slot.getIndex() < 4;
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
