package com.dweb.useful_interactive.block;

import com.dweb.useful_interactive.UsefulDecorMod;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModUtilityBlocks {
        //Ключи функциональных блоков
    public static final RegistryKey<Block> BATANIC_TABLE_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "batanic_table")
    );
    public static final RegistryKey<Block> HOUSEKEEPER_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "housekeeper")
    );



    
    //функциональные блоки
    public static final Block BATANIC_TABLE_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(AbstractBlock.Settings.create().registryKey(BATANIC_TABLE_KEY).strength(2.0f, 50.0f)), 
            BATANIC_TABLE_KEY
    );

    public static final Block HOUSEKEEPER_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(AbstractBlock.Settings.create().registryKey(HOUSEKEEPER_KEY).strength(4.0f, 50.0f)), 
            HOUSEKEEPER_KEY
    );

    public static void register() {}
}
