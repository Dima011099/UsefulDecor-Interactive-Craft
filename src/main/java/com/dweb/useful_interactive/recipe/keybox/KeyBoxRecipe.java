package com.dweb.useful_interactive.recipe.keybox;

import java.util.Map;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.IngredientPlacement;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

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


 
/* 
@Override
public boolean matches(RecipeInput input, World world) {
    int totalSlotsRequired = 0;
    for (String row : pattern) totalSlotsRequired += row.length();
    
    if (input.size() < totalSlotsRequired) {
        return false;
    }

    for (int y = 0; y < pattern.length; y++) {
        String row = pattern[y];
        for (int x = 0; x < row.length(); x++) {
            char c = row.charAt(x);
            int slotIndex = y * row.length() + x;

            if (slotIndex >= input.size()) {
                return c == ' '; 
            }

            ItemStack stack = input.getStackInSlot(slotIndex);

            if (c == ' ') {
                if (!stack.isEmpty()) return false;
            } else {
                Ingredient ingredient = key.get(c);
                // test(stack) — правильный метод для 2026 года
                if (ingredient == null || !ingredient.test(stack)) return false;
            }
        }
    }
    
    for (int i = totalSlotsRequired; i < input.size(); i++) {
        if (!input.getStackInSlot(i).isEmpty()) return false;
    }

    return true;
}
*/
@Override
public boolean matches(RecipeInput input, World world) {
    // pattern[0] — это твоя единственная строка в JSON (например, "S###")
    String row = pattern[0]; 
    
    // Проверяем соответствие ингредиентов в первых слотах
    for (int x = 0; x < row.length(); x++) {
        char c = row.charAt(x);
        ItemStack stack = input.getStackInSlot(x); // Прямой индекс 0, 1, 2, 3
        
        if (c == ' ') {
            if (!stack.isEmpty()) return false;
        } else {
            Ingredient ingredient = key.get(c);
            if (ingredient == null || !ingredient.test(stack)) return false;
        }
    }

    // Проверяем, что остальные слоты (если инвентарь больше паттерна) пусты
    for (int i = row.length(); i < input.size(); i++) {
        if (!input.getStackInSlot(i).isEmpty()) return false;
    }

    return true;
}


    @Override
    public ItemStack craft(RecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.output.copy();
    }


        @Override
        public RecipeSerializer<? extends Recipe<RecipeInput>> getSerializer() {
             return ModRecipeTypes.KEY_CABINET_SERIALIZER;
        }

        @Override
        public RecipeType<? extends Recipe<RecipeInput>> getType() {
            return ModRecipeTypes.KEY_CABINET;
        }



    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.NONE;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }


    public DefaultedList<Ingredient> getIngredients() {
    DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(pattern.length * pattern[0].length(), Ingredient.ofItems());

    for (int y = 0; y < pattern.length; y++) {
        String row = pattern[y];
        for (int x = 0; x < row.length(); x++) {
            char c = row.charAt(x);
            int index = y * row.length() + x;

            if (c == ' ') {
                ingredients.set(index, Ingredient.ofItems());
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

    public ItemStack getResult(RegistryWrapper.WrapperLookup registries) {
        return output;
    }

  public static class Serializer implements RecipeSerializer<KeyBoxRecipe> {
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
