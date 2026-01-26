package com.dweb.useful_interactive.recipe;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.recipe.keybox.KeyBoxRecipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModRecipeTypes {

    public static final RecipeType<KeyBoxRecipe> KEY_CABINET = registerType("key_box_craft");
    public static final RecipeSerializer<KeyBoxRecipe> KEY_CABINET_SERIALIZER = registerSerializer();

    private static RecipeType<KeyBoxRecipe> registerType(String id) {
        return Registry.register(Registries.RECIPE_TYPE, Identifier.of(UsefulDecorMod.MOD_ID, id), new RecipeType<>() {});
    }

    private static RecipeSerializer<KeyBoxRecipe> registerSerializer() {
        return Registry.register(
                Registries.RECIPE_SERIALIZER,
                Identifier.of(UsefulDecorMod.MOD_ID, "key_box_craft"),
                new KeyBoxRecipe.Serializer()
        );
    }

        // Метод для вызова в главном классе (onInitialize)
    public static void register() {
        // Просто обращение к классу активирует статические поля выше
        UsefulDecorMod.LOGGER.info("Registering Custom Recipes for " + UsefulDecorMod.MOD_ID);
    }
}