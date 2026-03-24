package com.dweb.useful_interactive.recipe;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.recipe.keybox.KeyBoxRecipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

@SuppressWarnings("null")/* 
public class ModRecipeTypes {

    public static final RecipeType<KeyBoxRecipe> KEY_CABINET = registerType("key_box_craft");
    public static final RecipeSerializer<KeyBoxRecipe> KEY_CABINET_SERIALIZER = registerSerializer();

    private static RecipeType<KeyBoxRecipe> registerType(String id) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, id), new RecipeType<>() {});
    }

    private static RecipeSerializer<KeyBoxRecipe> registerSerializer() {
        return Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "key_box_craft"), 
               new RecipeSerializer<>(KeyBoxRecipe.CODEC, KeyBoxRecipe.STREAM_CODEC)
        );
    }

    public static void register() {
        UsefulDecorMod.LOGGER.info("Registering Custom Recipes for " + UsefulDecorMod.MOD_ID);
    }
}*/
public class ModRecipeTypes {

    public static final RecipeType<KeyBoxRecipe> KEY_CABINET = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "key_box_craft"),
            new RecipeType<KeyBoxRecipe>() {
                @Override
                public String toString() { return "key_box_craft"; }
            }
    );

    public static final RecipeSerializer<KeyBoxRecipe> KEY_CABINET_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "key_box_craft"),
       
            new RecipeSerializer<>(KeyBoxRecipe.CODEC, KeyBoxRecipe.STREAM_CODEC)
    );

    public static void register() {
        UsefulDecorMod.LOGGER.info("Registering Custom Recipes for " + UsefulDecorMod.MOD_ID);
    }
}
