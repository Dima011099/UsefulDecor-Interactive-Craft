package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.UsefulDecorMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

;

public class ModBlocks {

    public static Block registerBlock(Block block, ResourceKey<Block> key) {//RegistryKey
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }


    public static void register() {
        UsefulDecorMod.LOGGER.debug("Регистрация блоков для " + UsefulDecorMod.MOD_ID);
        ModStorageBlocks.register();
        ModUtilityBlocks.register();
        ModArchitecturalBlocks.register();
    }
}