package com.dweb.useful_interactive.ui.keybox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dweb.useful_interactive.recipe.ModRecipeTypes;
import com.dweb.useful_interactive.recipe.keybox.KeyBoxRecipe;
import com.dweb.useful_interactive.registry.ModComponentType;
import com.dweb.useful_interactive.registry.items.KeyItem;
import com.dweb.useful_interactive.ui.ModScreenHandlers;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;

public class KeyBoxScreenHandler extends AbstractContainerMenu {
    private final TransientCraftingContainer inputInventory = new TransientCraftingContainer(this, 4, 1); //CraftingInventory
    private final ResultContainer resultInventory = new ResultContainer(); //CraftingResultInventory
    private final ContainerLevelAccess context; //ScreenHandlerContext -> ContainerLevelAccess
    private RecipeInput input;
    private boolean isCrafting = false;
    private List<ItemStack> lastInputSnapshot;
    private Player player;

    public KeyBoxScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(4), ContainerLevelAccess.NULL); //ScreenHandlerContext
    }

    public KeyBoxScreenHandler(int syncId, Inventory playerInventory, Container inventory, ContainerLevelAccess context) {//ScreenHandlerContext
        super(ModScreenHandlers.KEY_CABINET, syncId);
        this.context = context;
        this.player = playerInventory.player;
        inventory.startOpen(playerInventory.player); //onOpen
        lastInputSnapshot = createInputSnapshot();

        this.addSlot(new ResultSlot(playerInventory.player, inputInventory, resultInventory, 0, 134, 47){
            @Override
            public void onTake(Player player, ItemStack stack) {//onTakeItem
                ItemStack itemStack = inputInventory.getItem(0).copy();

                consumeIngredients(input);
                stack.getItem().onCraftedBy(stack, player); //getEntityWorld -> level onCraft

                inputInventory.setItem(0, itemStack); //setStack
                if(itemStack != ItemStack.EMPTY && itemStack.is(KeyItem.MY_ITEM))//itemStack.getItem() == KeyItem.MY_ITEM
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
    public ItemStack quickMoveStack(Player player, int invSlot) {//quickMove
        return ItemStack.EMPTY; 
    }

    @Override
    public void removed(Player player) { //onClosed
        super.removed(player);
    
        if (!player.level().isClientSide()) { //getEntityWorld -> level isClient
            this.clearContainer(player, this.inputInventory);  //dropInventory
            this.resultInventory.clearContent(); //clear
        }
    }

    @Override
    public boolean stillValid(Player player) {//stillValid
        return true;
    }

    @Override
    public void slotsChanged(Container inventory) {//onContentChanged
        super.slotsChanged(inventory);
    
        if (isCrafting) return;
    
        this.context.execute((world, pos) -> { //run -> execute
            if (!(world instanceof ServerLevel serverWorld)) return;
     
            List<ItemStack> currentInput = createInputSnapshot();

            if(inputsEqual(currentInput, lastInputSnapshot)){
                return;
            }
        

            input = new RecipeInput() {
                public int size() { return inputInventory.getContainerSize(); } //size
                @Override
                public ItemStack getItem(int slot) {//getStackInSlot
                    return inputInventory.getItem(slot);//getStack
                }
            };

            Optional<RecipeHolder<KeyBoxRecipe>> match = serverWorld.recipeAccess()//serverWorld.getRecipeManager()//RecipeEntry
                .getRecipeFor(ModRecipeTypes.KEY_CABINET, input, serverWorld);//getFirstMatch

            ItemStack result = ItemStack.EMPTY;
           
            isCrafting = true; 
            if (match.isPresent())
                result = match.get().value().assemble(input);
 
            lastInputSnapshot = createInputSnapshot();
            isCrafting = false; 
        

            resultInventory.setItem(0, result); //setStack
            
            inputInventory.setChanged(); //markDirty -> setChanged
            resultInventory.setChanged();  
       
            if (player instanceof ServerPlayer serverPlayer) { //ServerPlayerEntity -> ServerPlayer
                    serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(//networkHandler.sendPacket ScreenHandlerSlotUpdateS2CPacket
                    this.containerId,  //syncId
                    this.incrementStateId(), //nextRevision
                0,
                    result
                ));
            }
        });
    }

    private boolean inputsEqual(List<ItemStack> a, List<ItemStack> b) {
        if (a.size() != b.size()) return false;

        for (int i = 0; i < a.size(); i++) {
            if (!ItemStack.matches(a.get(i), b.get(i))) {//areEqual
                return false;
            }
        }
        return true;
    }

    private List<ItemStack> createInputSnapshot() {
        List<ItemStack> snapshot = new ArrayList<>();

        for (int i = 0; i < inputInventory.getContainerSize(); i++) {//size
            snapshot.add(inputInventory.getItem(i).copy());//getStack
        }

        return snapshot;
    }

    private void consumeIngredients(RecipeInput input) {
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);//getStackInSlot
            if (!stack.isEmpty()) {
                stack.shrink(1);//decrement
            }
        }
   } 
   
    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {//canInsertIntoSlot
        return slot.container == this.inputInventory && slot.getContainerSlot() < 4;//inventory getIndex
    }
   
}

class SpecificItemSlot extends Slot {
    public SpecificItemSlot(Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
/* 
    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() == KeyItem.MY_ITEM;
    }*/
   @Override
   public boolean mayPlace(ItemStack stack) {
        return stack.getItem() == KeyItem.MY_ITEM;
   }
}
