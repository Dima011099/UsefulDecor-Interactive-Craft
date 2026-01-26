package com.dweb.useful_interactive.ui.util;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record KeyBoxRecipeInput(ItemStack slot1, ItemStack slot2, ItemStack slot3, ItemStack slot4) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int index) {
        return switch (index) {
            case 0 -> slot1;
            case 1 -> slot2;
            case 2 -> slot3;
            case 3 -> slot4;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int size() { 
        return 4;
    }
}