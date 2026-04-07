package com.dweb.useful_interactive.recipe.keybox;

import java.util.Map;

import org.jspecify.annotations.NonNull;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.item.crafting.RecipeSerializer;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import com.dweb.useful_interactive.recipe.ModRecipeTypes;
import com.mojang.serialization.Codec;

public class KeyBoxRecipe implements Recipe<RecipeInput> {
   // private final ItemStack output;
     private final Item result;
    private final String[] pattern;
    private final Map<Character, Ingredient> key;

    public KeyBoxRecipe(String[] pattern, Map<Character, Ingredient> key, Item result /*ItemStack output*/) {
        this.key = key;
      //  this.output = output;
      this.result = result;
        this.pattern = pattern;
    }

@SuppressWarnings("null")
    @Override
    public boolean matches(RecipeInput input, Level world) {
        String row = pattern[0]; 
    
        for (int x = 0; x < row.length(); x++) {
            char c = row.charAt(x);
            ItemStack stack = input.getItem(x); 
        
            if (c == ' ') {
                if (!stack.isEmpty()) return false;
            } else {
                Ingredient ingredient = key.get(c);
                if (ingredient == null || !ingredient.test(stack)) return false;
            }
        }
        for (int i = row.length(); i < input.size(); i++) {
            if (!input.getItem(i).isEmpty()) return false; 
        }

        return true;
    }
@SuppressWarnings("null")
    @Override
    public ItemStack assemble(RecipeInput input) { 
       // return this.output.copy();
       return new ItemStack(result);
    }
@SuppressWarnings("null")
    @Override
public String group() {
    return "";
}

@Override
public boolean showNotification() {
    return true; 
}
@SuppressWarnings("null")
    @Override
public RecipeSerializer<? extends Recipe<RecipeInput>>  getSerializer() { 
    return ModRecipeTypes.KEY_CABINET_SERIALIZER; 
}
@SuppressWarnings("null")
    @Override
    public RecipeType<? extends Recipe<RecipeInput>> getType() {
        return ModRecipeTypes.KEY_CABINET;
    }
@SuppressWarnings("null")
    @Override
    public PlacementInfo placementInfo() { 
        return PlacementInfo.NOT_PLACEABLE;
    }
@SuppressWarnings("null")
    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }
    

@SuppressWarnings("null")
    public NonNullList<Ingredient> getIngredients() {
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
        //return output;
        return new ItemStack(result);
    }
@SuppressWarnings("null")
public static final MapCodec<KeyBoxRecipe> CODEC =
    RecordCodecBuilder.mapCodec(inst -> inst.group(

        // pattern
        Codec.STRING.listOf()
            .fieldOf("pattern")
            .forGetter(r -> java.util.Arrays.asList(r.pattern)),

        // key (String -> Ingredient → Character -> Ingredient)
        Codec.unboundedMap(Codec.STRING, Ingredient.CODEC)
            .fieldOf("key")
            .xmap(
                map -> {
                    Map<Character, Ingredient> result = new java.util.HashMap<>();
                    map.forEach((k, v) -> result.put(k.charAt(0), v));
                    return result;
                },
                map -> {
                    Map<String, Ingredient> result = new java.util.HashMap<>();
                    map.forEach((k, v) -> result.put(String.valueOf(k), v));
                    return result;
                }
            )
            .forGetter(r -> r.key),

        // ✅ ВАЖНО: ТОЛЬКО Item, НЕ ItemStack
        BuiltInRegistries.ITEM.byNameCodec()
            .fieldOf("result")
            .forGetter(r -> r.result)

    ).apply(inst, (patternList, key, item) ->
        new KeyBoxRecipe(
            patternList.toArray(new String[0]),
            key,
            item // теперь это Item
        )
    ));
@SuppressWarnings("null")/*
public static final StreamCodec<RegistryFriendlyByteBuf, KeyBoxRecipe> STREAM_CODEC =
    StreamCodec.composite(

        ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()),
        r -> java.util.Arrays.asList(r.pattern),

        ByteBufCodecs.map(
            java.util.HashMap::new,
            ByteBufCodecs.STRING_UTF8.map(s -> s.charAt(0), String::valueOf),
            Ingredient.CONTENTS_STREAM_CODEC
        ),
        r -> r.key,

        ItemStack.STREAM_CODEC,
        r -> r.output,

        (pattern, key, result) ->
            new KeyBoxRecipe(pattern.toArray(String[]::new), key, result)
    );*/
    public static final StreamCodec<RegistryFriendlyByteBuf, KeyBoxRecipe> STREAM_CODEC =
    StreamCodec.composite(

        // pattern
        ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()),
        r -> java.util.Arrays.asList(r.pattern),

        // key
        ByteBufCodecs.map(
            java.util.HashMap::new,
            ByteBufCodecs.STRING_UTF8.map(s -> s.charAt(0), String::valueOf),
            Ingredient.CONTENTS_STREAM_CODEC
        ),
        r -> r.key,

        // result → теперь Item, а не ItemStack
        ByteBufCodecs.registry(Registries.ITEM),
        r -> r.result,

        // сборка
        (pattern, key, item) ->
            new KeyBoxRecipe(pattern.toArray(String[]::new), key, item)
    );

}

/*
    public static final MapCodec<KeyBoxRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
        Codec.STRING.listOf().fieldOf("pattern").forGetter(r -> java.util.Arrays.asList(r.pattern)),
        Codec.unboundedMap(Codec.string(1, 1), Ingredient.CODEC).fieldOf("key").forGetter(r -> {
            Map<String, Ingredient> map = new HashMap<>();
            r.key.forEach((c, ing) -> map.put(c.toString(), ing));
            return map;
        }),
        ItemStack.OPTIONAL_CODEC.fieldOf("result").forGetter(r -> r.output)
    ).apply(inst, (p, k, r) -> {
        Map<Character, Ingredient> charKey = new HashMap<>();
        k.forEach((s, ing) -> charKey.put(s.charAt(0), ing));
        return new KeyBoxRecipe(p.toArray(new String[0]), charKey, r);
    })); */