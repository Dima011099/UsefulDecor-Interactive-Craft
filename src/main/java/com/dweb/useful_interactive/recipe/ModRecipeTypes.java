package com.dweb.useful_interactive.common;

import com.dweb.useful_interactive.UsefulDecorMod;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModRecipeTypes {

    public static final RecipeType<KeyCabinetRecipe> KEY_CABINET = registerType("key_box_craft");
    public static final RecipeSerializer<KeyCabinetRecipe> KEY_CABINET_SERIALIZER = registerSerializer();

    private static RecipeType<KeyCabinetRecipe> registerType(String id) {
        return Registry.register(Registries.RECIPE_TYPE, Identifier.of(UsefulDecorMod.MOD_ID, id), new RecipeType<>() {});
    }

    private static RecipeSerializer<KeyCabinetRecipe> registerSerializer() {
        return Registry.register(
                Registries.RECIPE_SERIALIZER,
                Identifier.of(UsefulDecorMod.MOD_ID, "key_box_craft"),
                new KeyCabinetRecipe.Serializer()
        );
    }

        // Метод для вызова в главном классе (onInitialize)
    public static void register() {
        // Просто обращение к классу активирует статические поля выше
        UsefulDecorMod.LOGGER.info("Registering Custom Recipes for " + UsefulDecorMod.MOD_ID);
    }
}