package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.UsefulDecorMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
* Main registry facade for all mod blocks.
* Coordinates sub-registry classes and handles automatic BlockItem creation.
*/
@SuppressWarnings("null")
public class ModBlocks {
    /**
    * Registers a block and automatically creates its corresponding {@link BlockItem}.
    * This ensures the block can be held in inventory and placed in the world.
    *
    * @param block the block instance to register
    * @param key   the unique registry key of the block
    * @return the registered block instance
    */
   /* 
    public static Block registerBlock(Block block, ResourceKey<Block> key) {//RegistryKey
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }*/

    public static Block registerBlock(Block block, ResourceKey<Block> key) {
        Block registeredBlock = Registry.register(BuiltInRegistries.BLOCK, key, block);

        Identifier blockId = BuiltInRegistries.BLOCK.getKey(registeredBlock);
        
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, blockId);
        Registry.register(
                BuiltInRegistries.ITEM, 
                itemKey, 
                new BlockItem(registeredBlock, new Item.Properties().setId(itemKey))
        );

        return registeredBlock;
    }

    
    /**
    * Helper factory method to create a synchronized block registry key.
    * Used across all sub-registry files to prevent code duplication.
    *
    * @param path the unique identifier path of the block
    * @return a {@link ResourceKey} bound to the block registry
    */
    public static ResourceKey<Block> createBlockKey(String path) {
        return ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, path)
        );
    }

    /**
    * Central initialization entrypoint for all block registries.
    * Invoked during mod construction to force deterministic class loading.
    */
    public static void register() {
        UsefulDecorMod.LOGGER.info("Initializing block registries for " + UsefulDecorMod.MOD_ID);

        ModStorageBlocks.register();
        ModUtilityBlocks.register();
        ModArchitecturalBlocks.register();
    }
}