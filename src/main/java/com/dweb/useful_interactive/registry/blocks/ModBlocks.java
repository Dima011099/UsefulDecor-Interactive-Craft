package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.UsefulDecorMod;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class ModBlocks {

    public static Block registerBlock(Block block, RegistryKey<Block> key) {
        return Registry.register(Registries.BLOCK, key, block);
    }


    public static void register() {
        UsefulDecorMod.LOGGER.debug("Регистрация блоков для " + UsefulDecorMod.MOD_ID);
        ModStorageBlocks.register();
        ModUtilityBlocks.register();
        ModArchitecturalBlocks.register();
    }
}