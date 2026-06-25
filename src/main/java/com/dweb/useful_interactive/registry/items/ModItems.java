package com.dweb.useful_interactive.registry.items;


import com.dweb.useful_interactive.UsefulDecorMod;/*
import com.dweb.useful_interactive.registry.blocks.ModArchitecturalBlocks;
import com.dweb.useful_interactive.registry.blocks.ModStorageBlocks;
import com.dweb.useful_interactive.registry.blocks.ModUtilityBlocks;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;*/

public class ModItems {
   /*  private static Item registerBlockItem(Block block, ResourceKey<Item> key) {
        return Registry.register(BuiltInRegistries.ITEM, key, new BlockItem(block, new Item.Properties().setId(key)));
    }*/

    public static void register() {
        UsefulDecorMod.LOGGER.debug("Регистрация блоков для " + UsefulDecorMod.MOD_ID);
    }
}