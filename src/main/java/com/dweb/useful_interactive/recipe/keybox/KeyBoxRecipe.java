package com.dweb.useful_interactive.recipe.keybox;

import java.util.Map;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.item.crafting.RecipeSerializer;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializers;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import com.dweb.useful_interactive.recipe.ModRecipeTypes;
import com.mojang.serialization.Codec;

import java.util.Arrays;
import java.util.HashMap; 

public class KeyBoxRecipe implements Recipe<RecipeInput> {
    private final ItemStack output;
    private final String[] pattern;
    private final Map<Character, Ingredient> key;

    public KeyBoxRecipe(String[] pattern, Map<Character, Ingredient> key, ItemStack output) {
        this.key = key;
        this.output = output;
        this.pattern = pattern;
    }

    @Override
    public boolean matches(RecipeInput input, Level world) {
        String row = pattern[0]; 
    
        for (int x = 0; x < row.length(); x++) {
            char c = row.charAt(x);
            ItemStack stack = input.getItem(x); //getStackInSlot(x)
        
            if (c == ' ') {
                if (!stack.isEmpty()) return false;
            } else {
                Ingredient ingredient = key.get(c);
                if (ingredient == null || !ingredient.test(stack)) return false;
            }
        }
        for (int i = row.length(); i < input.size(); i++) {
            if (!input.getItem(i).isEmpty()) return false; //getStackInSlot(i)
        }

        return true;
    }

    @Override
    public ItemStack assemble(RecipeInput input) { //craft(RegistryWrapper.WrapperLookup )
        return this.output.copy();
    }

    @Override
public String group() {
    return ""; // Группа рецепта в книге (пустая строка по дефолту)
}

@Override
public boolean showNotification() {
    return true; // Показывать ли уведомление о разблокировке рецепта
}
/* *
    @Override
    public RecipeSerializer<? extends Recipe<RecipeInput>> getSerializer() {
        return ModRecipeTypes.KEY_CABINET_SERIALIZER;
    }
*/
    @Override
public RecipeSerializer<KeyBoxRecipe> getSerializer() { // Убери wildcard <? extends ...>
    return ModRecipeTypes.KEY_CABINET_SERIALIZER; 
}

    @Override
    public RecipeType<? extends Recipe<RecipeInput>> getType() {
        return ModRecipeTypes.KEY_CABINET;
    }

    @Override
    public PlacementInfo placementInfo() { //placementInfo
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {//getRecipeBookCategory
        return null;
    }


    public NonNullList<Ingredient> getIngredients() {// DefaultedList
        NonNullList<Ingredient> ingredients = NonNullList.withSize(pattern.length * pattern[0].length(), Ingredient.of());//Ingredient.ofItems()

        for (int y = 0; y < pattern.length; y++) {
            String row = pattern[y];
            for (int x = 0; x < row.length(); x++) {
                char c = row.charAt(x);
                int index = y * row.length() + x;

                if (c == ' ') {
                    ingredients.set(index, Ingredient.of());
                } else {
                    ingredients.set(index, key.get(c)); // key.get(c) → Ingredient
                }
            }
        }

        return ingredients;
    }

    public boolean fits(int width, int height) {
        return true;
    }
    

    public ItemStack getResult(HolderLookup.Provider registries) {//RegistryWrapper.WrapperLookup
        return output;
    }

    public static class Serializer /*implements RecipeSerializer<KeyBoxRecipe>*/ {
        private static final Codec<Map<Character, Ingredient>> KEY_CODEC = Codec.unboundedMap(
            Codec.string(1, 1).xmap(s -> s.charAt(0), String::valueOf),
            Ingredient.CODEC
        );

        public static final MapCodec<KeyBoxRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Codec.STRING.listOf().fieldOf("pattern").xmap(
                list -> list.toArray(String[]::new), 
                Arrays::asList
            ).forGetter(recipe -> recipe.pattern),
            KEY_CODEC.fieldOf("key").forGetter(recipe -> recipe.key),
            ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(recipe -> recipe.output)
        ).apply(inst, KeyBoxRecipe::new));

        @Override
        public MapCodec<KeyBoxRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, KeyBoxRecipe> packetCodec() {
            return PacketCodec.ofStatic(
                (buf, recipe) -> {
                    buf.writeVarInt(recipe.pattern.length);
                    for (String s : recipe.pattern) buf.writeString(s);
                    buf.writeVarInt(recipe.key.size());
                    recipe.key.forEach((c, ing) -> {
                        buf.writeChar(c);
                        Ingredient.PACKET_CODEC.encode(buf, ing);
                    });
                    ItemStack.PACKET_CODEC.encode(buf, recipe.output);
                },
                buf -> {
                    int len = buf.readVarInt();
                    String[] pattern = new String[len];
                    for (int i = 0; i < len; i++) pattern[i] = buf.readString();
                    int keySize = buf.readVarInt();
                    Map<Character, Ingredient> key = new HashMap<>();
                    for (int i = 0; i < keySize; i++) key.put(buf.readChar(), Ingredient.PACKET_CODEC.decode(buf));
                    return new KeyBoxRecipe(pattern, key, ItemStack.PACKET_CODEC.decode(buf));
                }
            );
        }
    }
}
