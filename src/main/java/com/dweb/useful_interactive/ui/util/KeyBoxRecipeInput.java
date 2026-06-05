package com.dweb.useful_interactive.ui.util;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * A custom recipe input wrapper for the Key Box container.
 * Encapsulates four distinct item slots used as ingredients in crafting recipes.
 */
public record KeyBoxRecipeInput(ItemStack slot1, ItemStack slot2, ItemStack slot3, ItemStack slot4) implements RecipeInput {
    
    /**
    * Retrieves the item stack from the specified slot index.
    * Maps indexes 0-3 to the respective structural slots of the key box.
    *
    * @param index the slot index (0 to 3)
    * @return the corresponding {@link ItemStack}, or {@link ItemStack#EMPTY} if out of bounds or null
    */
    @Override
    @SuppressWarnings("null")
    public ItemStack getItem(int index) {
        ItemStack selectedSlot = switch (index) {
            case 0 -> slot1;
            case 1 -> slot2;
            case 2 -> slot3;
            case 3 -> slot4;
            default -> ItemStack.EMPTY;
        };

        return selectedSlot == null ? ItemStack.EMPTY : selectedSlot;
    }

    @Override
    public int size() { 
        return 4;
    }
}